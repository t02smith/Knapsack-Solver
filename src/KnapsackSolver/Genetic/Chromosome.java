package KnapsackSolver.Genetic;

public class Chromosome {
    //How good of a solution this chromosome represents
    private int fitness;

    //Weight of the chromosome -> -1 if invalid
    private int weight = -1;

    //Which items this chromosome uses
    private boolean[] genes;

    /**
     * Creates a new chromosome
     * @param genes This chromosomes set of items
     */
    public Chromosome(boolean[] genes) {
        this.genes = genes;
    }

    /**
     * Sets the fitness of the chromosome
     * @param fitness
     */
    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * Flips a given gene
     * Makes the fitness outdated
     * @param index The gene being flipped
     */
    public void flipGene(int index) {
        this.genes[index] = !this.genes[index];
    }

    /**
     * Sets a gene to a given value
     * @param index
     * @param gene
     */
    public void setGene(int index, boolean gene) {
        this.genes[index] = gene;
    }

    //GETTERS//

    public int getFitness() {
        return this.fitness;
    }

    public boolean[] getGenes() {
        return this.genes;
    }

    public boolean getGene(int index) {
        return this.genes[index];
    }

    public int getWeight() {
        return this.weight;
    }

    public int getNumOfGenes() {
        return this.genes.length;
    }

    //

    @Override
    public String toString() {
        var output = new StringBuilder();

        for (boolean gene: genes) {
            output.append(gene ? '1': '0');
        }

        return output.toString();
    }

}
