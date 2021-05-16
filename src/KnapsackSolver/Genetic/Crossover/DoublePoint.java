package KnapsackSolver.Genetic.Crossover;

import java.util.Random;

import KnapsackSolver.Genetic.Chromosome;
import KnapsackSolver.Genetic.Generation;


public class DoublePoint implements Crossover {
    @Override
    public void crossover(Chromosome c1, Chromosome c2) {
        var random = new Random();

        int lower = random.nextInt(c1.getNumOfGenes());
        int upper = lower + random.nextInt(c1.getNumOfGenes()-lower);

        for (; lower <= upper; lower++) {
            boolean temp = c1.getGene(lower);
            c1.setGene(lower, c2.getGene(lower));
            c2.setGene(lower, temp);
        }
    }

    @Override
    public void crossover(Generation g) {
        for (int i = 0; i < g.getGenerationSize()-1; i++) {
            this.crossover(g.getChromosome(i), g.getChromosome(i+1));
        }
    }
}
