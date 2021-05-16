package KnapsackSolver.Genetic.Fitness;

import KnapsackSolver.Config;
import KnapsackSolver.Genetic.Chromosome;

/**
 * The fitness value of the chromosome is
 * equal to its total value
 * 
 * If the weight is over the capacity then
 * its fitness is 0
 */
public class Value implements FitnessAlg {
    @Override
    public int fitness(Chromosome c) {
        if (c.getWeight() > Config.CAPACITY) return 0;

        return c.getValue();
    }
}
