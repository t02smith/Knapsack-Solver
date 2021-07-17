package KnapsackSolver.Genetic;

/**
 * Represents an optimal chromosome
 * Stores the generation in which it was discovered
 */
public class OptimalChromosome extends Chromosome {
    //The generation in which the optimal chromosome was found
    private final int generationFound;

    public OptimalChromosome (Chromosome c, int generationFound) {
        super(c.getGenes());
        this.fitness = c.getFitness();
        this.generationFound = generationFound;

    }

    public int getGenerationFound() {
        return this.generationFound;
    }
}
