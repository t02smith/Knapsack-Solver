package KnapsackSolver.Genetic.Mutation;

import KnapsackSolver.Config;
import KnapsackSolver.Genetic.Chromosome;

/**
 * Looks at the genes a chromosome has and removes
 * the gene with the lowest value/weight ratio
 * 
 * Most effective so far
 */
public class RemoveLeastValue implements Mutation {
    @Override
    public void mutate(Chromosome c) {
        double lowestValuePerWeight = Double.MAX_VALUE;
        int lowestPos = -1;

        var genes = c.getGenes();
        for (int i = 0; i < genes.length; i++) {
            if (genes[i]) {
                var item = Config.ITEMS[i];
                double valuePerWeight = item.getValue()/item.getWeight();

                if (valuePerWeight < lowestValuePerWeight) {
                    lowestValuePerWeight = valuePerWeight;
                    lowestPos = i;
                }
            }
        }

        if (lowestPos >= 0) c.flipGene(lowestPos); 
    }
}
