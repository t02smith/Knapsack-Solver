package KnapsackSolver.Genetic.Mutation;

import KnapsackSolver.Genetic.Chromosome;

public interface Mutation {
    public void mutate(Chromosome c);

    /**
     * Instances of any mutations
     */ 
    public enum Mutations {
        RANDOM                  (new RandomMutation()),
        FILL_CAPACITY           (new FillCapacity()),
        REMOVE_LEAST_VALUABLE   (new RemoveLeastValue()),
        DOUBLE_MUTATION         (new DoubleMutation(REMOVE_LEAST_VALUABLE, FILL_CAPACITY));
    
        private Mutation mutation;
    
        private Mutations(Mutation mutation) {
            this.mutation = mutation;
        }
    
        /**
         * Calls the mutation function on a chromosome
         * @param c chromosome
         */
        public void mutate(Chromosome c) {
            this.mutation.mutate(c);
        }
    }
}
