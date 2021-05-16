package KnapsackSolver.Genetic.Crossover;

import java.util.Random;

import KnapsackSolver.Genetic.Chromosome;
import KnapsackSolver.Genetic.Generation;

/**
 * A single point crossover selects an index at random
 * and swaps all genes from that point to the end of
 * the chromosome
 */
public class SinglePoint implements Crossover {
    @Override
    public void crossover(Chromosome c1, Chromosome c2) {
        var random = new Random();

        int point = random.nextInt(c1.getNumOfGenes());

        for (; point < c1.getNumOfGenes(); point++) {
            boolean temp = c1.getGene(point);
            c1.setGene(point, c2.getGene(point));
            c2.setGene(point, temp);
        }
    }

    @Override
    public void crossover(Generation g) {
        for (int i = 0; i < g.getGenerationSize()-1; i++) {
            this.crossover(g.getChromosome(i), g.getChromosome(i+1));
        }
    }
}
