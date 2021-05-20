package KnapsackSolver.Genetic;

import java.util.Comparator;

/**
 * Ways we can sort a list of chromosome
 */
public enum SortChromosomeBy implements Comparator<Chromosome> {
    FITNESS {
        @Override
        public int compare(Chromosome c1, Chromosome c2) {
            if (c1.getFitness() >= c2.getFitness()) return 1;
            else return -1;
        }
    },
    VALUE {
        @Override
        public int compare(Chromosome c1, Chromosome c2) {
            if (c1.getValue() >= c2.getValue()) return 1;
            else return -1;
        }
    },
    WEIGHT {
        @Override
        public int compare(Chromosome c1, Chromosome c2) {
            if (c1.getWeight() >= c2.getWeight()) return -1;
            else return 1;
        }
    };
}
