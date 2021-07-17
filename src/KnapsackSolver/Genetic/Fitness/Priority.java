package KnapsackSolver.Genetic.Fitness;

import KnapsackSolver.Config;
import KnapsackSolver.Genetic.Chromosome;

public class Priority implements FitnessAlg {

    //The index's of the items that must be in the knapsack
    private static final int[] REQUIRED_ITEMS = new int[] {2};

    @Override
    public int fitness(Chromosome c) {
 
        //Checks the chromosome has all the required genes
        for (int gene: REQUIRED_ITEMS) {
            if (!c.getGene(gene)) return 0;
        }

        //Give fitness by value
        return (c.getWeight() > Config.CAPACITY ? 0: c.getValue());
        
    }
}
