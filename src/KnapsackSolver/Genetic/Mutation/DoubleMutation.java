package KnapsackSolver.Genetic.Mutation;

import KnapsackSolver.Genetic.Chromosome;

/**
 * Applies a two different mutatins back to back
 */
public class DoubleMutation implements Mutation {
    //The mutation called first
    private Mutations first;

    //The mutation called second
    private Mutations second;

    /**
     * Creates a new double mutation
     * @param first
     * @param second
     */
    public DoubleMutation(Mutations first, Mutations second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public void mutate(Chromosome c) {
        this.first.mutate(c);
        this.second.mutate(c);
    }
   
}
