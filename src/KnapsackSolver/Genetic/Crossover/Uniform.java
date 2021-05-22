package KnapsackSolver.Genetic.Crossover;

import java.util.Random;

import KnapsackSolver.Config;
import KnapsackSolver.Genetic.Chromosome;

public class Uniform implements Crossover {
    @Override
    public void crossover(Chromosome c1, Chromosome c2) {
        var random = new Random();

        for (int i = 0; i < Config.ITEMS.length; i++) {
            double chance = random.nextDouble();

            if (random.nextDouble() < chance) {
                var c1Gene = c1.getGene(i);
                c1.setGene(i, c2.getGene(i));
                c2.setGene(i, c1Gene);
            }
        }
        
    }

}
