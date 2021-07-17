package KnapsackSolver.Genetic;

import KnapsackSolver.Config;

/**
 * A chromsome that stores what items are included
 */
public class Chromosome {
    //How good of a solution this chromosome represents
    protected int fitness;

    //Weight of the chromosome -> -1 if invalid
    protected int weight;

    //The value of the items in the chromosome
    protected int value;

    //Which items this chromosome uses
    protected boolean[] genes;

    /**
     * Creates a new chromosome
     * @param genes This chromosomes set of items
     */
    public Chromosome(boolean[] genes) {
        this.genes = genes;
        this.updateValues();
    }

    public Chromosome(String genes) {
        this.genes = this.stringToBool(genes);
        this.updateValues();
    }

    /**
     * Check for updates to the value and weight attributes
     */
    private void updateValues() {
        this.calculateValue();
        this.calculateWeight();
    }

    /**
     * Calculate the weight of the items
     */
    private void calculateWeight() {
        this.weight = 0;

        for (int i = 0; i < this.genes.length; i++) {
            if (this.genes[i]) this.weight += Config.ITEMS[i].getWeight();
        }
    }

    /**
     * Calculate the total value of the items
     */
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

    protected boolean[] stringToBool(String c) {
        if (c.length() != Config.ITEMS.length) throw new IllegalArgumentException("Incorrect number of items.");

        boolean[] chrom = new boolean[Config.ITEMS.length];
        for (int i = 0; i < chrom.length; i++) {
            if (c.charAt(i) == '1') chrom[i] = true;
            else if (c.charAt(i) == '0') chrom[i] = false;
            else throw new IllegalArgumentException("Incorrect chromosome given - expected 1/0 not '" + c.charAt(i) + "'");
        }

        return chrom;
    }

    @Override
    public String toString() {
        var output = new StringBuilder();

        for (boolean gene: genes) {
            output.append(gene ? '1': '0');
        }

        return output.toString();
    }

}
