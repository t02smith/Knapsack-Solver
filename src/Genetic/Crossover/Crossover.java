package Genetic.Crossover;

import Genetic.Chromosome;

/**
 * Interface for all crossover types
 */
public interface Crossover {
    public void crossover(Chromosome c1, Chromosome c2);

    /**
     * Instance for every crossover for a simpler interface to use them
     */
    public enum Crossovers {
        SINGLE (new SinglePoint());

        private Crossover crossover;

        private Crossovers(Crossover crossover) {
            this.crossover = crossover;
        }

        public void crossover(Chromosome c1, Chromosome c2) {
            this.crossover.crossover(c1, c2);
        }
    }
}
