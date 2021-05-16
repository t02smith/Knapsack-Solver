package KnapsackSolver.Genetic.Fitness;

import KnapsackSolver.Config;
import KnapsackSolver.Genetic.Chromosome;

/**
 * This algorithm will try and maximise the weight
 * of the knapsack in the hopes that by fitting more
 * in we can increase our value
 * 
 * If the weight is over the capacity then
 * its fitness is 0
 */
public class Weight implements FitnessAlg {
    @Override
    public int fitness(Chromosome c) {
        if (c.getWeight() > Config.CAPACITY) return 0;

        return c.getWeight();
    }
}
