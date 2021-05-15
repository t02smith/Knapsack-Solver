package Genetic.Mutation;

import Genetic.Chromosome;

public interface Mutation {
    public void mutate(Chromosome c);

    /**
     * Instances of any mutations
     */
    public enum Mutations {
        RANDOM (new RandomMutation());
    
        private Mutation mutation;
    
        private Mutations(Mutation mutation) {
            this.mutation = mutation;
        }
    
        public void mutate(Chromosome c) {
            this.mutation.mutate(c);
        }
    }
}
