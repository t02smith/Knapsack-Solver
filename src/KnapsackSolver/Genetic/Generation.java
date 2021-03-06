package KnapsackSolver.Genetic;

public class Generation {
    //Chromosomes in the generation
    private Chromosome[] chromosomes;

    /**
     * Creates a new generation
     * @param chromosomes The chromosomes in the generation
     */
    public Generation(Chromosome[] chromosomes) {
        this.chromosomes = chromosomes; 
    }

    /**
     * Gets the most optimal chromosome from that generation
     * @return optimal chromosome
     */
    public Chromosome getOptimal() {
        var optimal = this.chromosomes[1];

        for (int i = 1; i < this.chromosomes.length; i++) {
            if (this.chromosomes[i].getFitness() > optimal.getFitness()) {
                optimal = this.chromosomes[i];
            }
        }

        return optimal;
    }

    public void setChromosome(int index, Chromosome c) {
        this.chromosomes[index] = c;
    }

    //GETTERS//

    public Chromosome[] getChromosomes() {
        return this.chromosomes;
    }

    public Chromosome getChromosome(int index) {
        return this.chromosomes[index];
    }

    public int getGenerationSize() {
        return this.chromosomes.length;
    }


}
