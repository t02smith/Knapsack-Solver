package KnapsackSolver.Genetic.Crossover;

import java.util.Arrays;

import KnapsackSolver.Genetic.Chromosome;
import KnapsackSolver.Genetic.Generation;
import KnapsackSolver.Genetic.SortChromosomeBy;

/**
 * Interface for all crossover types
 */
public interface Crossover {
    //Crossover two chromosomes
    public void crossover(Chromosome c1, Chromosome c2);
    
    //Perform a crossover for the entire generation
    public default void crossover(Generation g) {
        Arrays.sort(g.getChromosomes(), SortChromosomeBy.VALUE);

        for (int i = 0; i < g.getGenerationSize()-1; i++) {
            this.crossover(g.getChromosome(i), g.getChromosome(i+1));
        }
    }

    /**
     * Instance for every crossover for a simpler interface to use them
     */
    public enum Crossovers {
        SINGLE (new SinglePoint()),
        DOUBLE (new DoublePoint()),
        UNIFORM (new Uniform());

        private Crossover crossover;

        private Crossovers(Crossover crossover) {
            this.crossover = crossover;
        }

        public void crossover(Generation g) {
            this.crossover.crossover(g);
        }
    }
}
