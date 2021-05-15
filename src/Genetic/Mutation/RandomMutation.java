package Genetic.Mutation;

import java.util.Random;

import Genetic.Chromosome;

/**
 * This mutation will randomly flip bits in a chromosome
 * Each iteration the chance to be flipped is randomly generated
 */
public class RandomMutation implements Mutation {

    @Override
    public void mutate(Chromosome c) {
        if (c == null) {
            System.out.println("it worked");
            return;
        }

        var random = new Random();

        for (int i = 0; i < c.getNumOfGenes(); i++) {
            double chance = random.nextDouble();

            if (random.nextDouble() < chance) c.flipGene(i);
        }
    }
}
