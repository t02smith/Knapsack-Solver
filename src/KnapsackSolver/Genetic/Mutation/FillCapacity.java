package KnapsackSolver.Genetic.Mutation;

import KnapsackSolver.Config;
import KnapsackSolver.Genetic.Chromosome;

/**
 * Will loop through a chromosome and if you can add the item
 * to the bag and stay within the weight limit it adds it
 */
public class FillCapacity implements Mutation {
    @Override
    public void mutate(Chromosome c) {
        //The chromosome exceeds the weight limit
        if (c.getFitness() == 0) return;

        boolean[] genes = c.getGenes();
        int weight = c.getWeight();

        for (int i = 0; i < genes.length; i++) {
            if (weight == Config.CAPACITY) break;

            if (!genes[i]) {
                if (weight + Config.ITEMS[i].getWeight() <= Config.CAPACITY) {
                    c.flipGene(i);
                }
            }
        }
        
    }
}
