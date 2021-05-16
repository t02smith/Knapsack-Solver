package KnapsackSolver.Genetic;

import KnapsackSolver.Config;

/**
 * A chromsome that stores what items are included
 * TODO more efficient setter
 */
public class Chromosome {
    //How good of a solution this chromosome represents
    private int fitness;

    //Weight of the chromosome -> -1 if invalid
    private int weight;

    //The value of the items in the chromosome
    private int value;

    //Which items this chromosome uses
    private boolean[] genes;

    /**
     * Creates a new chromosome
     * @param genes This chromosomes set of items
     */
    public Chromosome(boolean[] genes) {
        this.genes = genes;
        this.updateValues();
    }

    private void updateValues() {
        this.calculateValue();
        this.calculateWeight();
    }

    private void calculateWeight() {
        this.weight = 0;

        for (int i = 0; i < this.genes.length; i++) {
            if (this.genes[i]) this.weight += Config.ITEMS[i].getWeight();
        }
    }

    private void calculateValue() {
        this.value = 0;

        for (int i = 0; i < this.genes.length; i++) {
            if (this.genes[i]) this.value += Config.ITEMS[i].getValue();
        }
    }

    /**
     * Sets the fitness of the chromosome
     * @param fitness
     */
    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    /**
     * Flips a given gene
     * Makes the fitness outdated
     * @param index The gene being flipped
     */
    public void flipGene(int index) {
        this.genes[index] = !this.genes[index];
        this.updateValues();
    }

    /**
     * Sets a gene to a given value
     * @param index
     * @param gene
     */
    public void setGene(int index, boolean gene) {
        this.genes[index] = gene;
        this.updateValues();
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

    public int getValue() {
        return this.value;
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
