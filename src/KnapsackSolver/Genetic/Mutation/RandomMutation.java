package KnapsackSolver.Genetic.Mutation;

import java.util.Random;

import KnapsackSolver.Genetic.Chromosome;


/**
 * This mutation will randomly flip bits in a chromosome
 * Each iteration the chance to be flipped is randomly generated
 */
public class RandomMutation implements Mutation {

    @Override
    public void mutate(Chromosome c) {
        var random = new Random();

        for (int i = 0; i < c.getNumOfGenes(); i++) {
            double chance = random.nextDouble();

            if (random.nextDouble() < chance) c.flipGene(i);
        }
    }
}
