package KnapsackSolver.Genetic.Crossover;

import KnapsackSolver.Genetic.Chromosome;
import KnapsackSolver.Genetic.Generation;

/**
 * Interface for all crossover types
 */
public interface Crossover {
    //Crossover two chromosomes
    public void crossover(Chromosome c1, Chromosome c2);
    
    //Perform a crossover for the entire generation
    public void crossover(Generation g);

    /**
     * Instance for every crossover for a simpler interface to use them
     */
    public enum Crossovers {
        SINGLE (new SinglePoint()),
        DOUBLE (new DoublePoint());

        private Crossover crossover;

        private Crossovers(Crossover crossover) {
            this.crossover = crossover;
        }

        public void crossover(Generation g) {
            this.crossover.crossover(g);
        }
    }
}
