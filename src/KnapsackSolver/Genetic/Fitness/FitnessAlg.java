package KnapsackSolver.Genetic.Fitness;

import KnapsackSolver.Genetic.Chromosome;

public interface FitnessAlg {
    public int fitness(Chromosome c);

    public enum Fitness {
        VALUE (new Value()),
        WEIGHT (new Weight()),
        PRIORITY (new Priority());

        private FitnessAlg fitness;

        private Fitness(FitnessAlg f) {
            this.fitness = f;
        }

        public int fitness(Chromosome c) {
            return this.fitness.fitness(c);
        }
    }
}
