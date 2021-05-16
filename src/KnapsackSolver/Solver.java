package KnapsackSolver;
import java.util.ArrayList;
import java.util.Random;

import KnapsackSolver.Genetic.Chromosome;
import KnapsackSolver.Genetic.Generation;


public class Solver {

    //The current optimal chromosome
    private Chromosome optimal;
    private int optimalFound;


    //List of generations
    private ArrayList<Generation> generations = new ArrayList<>();

    public static void main(String[] args) {
        var solver = new Solver();

        System.out.printf("%d: %d, %d\n", solver.getOptimalFound(), solver.getOptimal().getFitness(), solver.getOptimal().getWeight());
        System.out.println(solver.getOptimal());
    }

    public Solver() {
        this.mainLoop();
    }

    /**
     * Main loop for iterating through generations
     */
    private void mainLoop() {
        //Creates an initial random first generation
        this.createRandomGeneration();

        /*
         * Repeat for a given number of generations
         */
        while (this.generations.size() <= Config.GENERATION_LIMIT) {
            var generation = this.generations.get(this.generations.size()-1);
            Chromosome[] chromosomes = generation.getChromosomes();

            this.setFitnesses(generation);

            //If a new optimal if found
            if (this.optimal == null || generation.getOptimal().getFitness() > this.optimal.getFitness()) {
                this.optimal = generation.getOptimal();
                this.optimalFound = this.generations.size();
            }

            if (this.generations.size() == Config.GENERATION_LIMIT) break;

            //CREATE NEXT GENERATION            

            int sum = 0;
            for (Chromosome c: chromosomes) {
                sum += c.getFitness();
            }

            //var nextGen = new Chromosome[CHROMOSOMES_PER_GENERATION];
            var nextGen = new Generation(new Chromosome[Config.CHROMOSOMES_PER_GENERATION]);

            //MUTATION//
            for (int i = 0; i < Config.CHROMOSOMES_PER_GENERATION; i++) {
                nextGen.setChromosome(i, this.keepChromosome(chromosomes[i], sum) 
                    ? chromosomes[i] 
                    : this.generateRandomChromosome()
                );
                
                //Each chromosome is mutated
                Config.MUTATION.mutate(nextGen.getChromosome(i));
            }

            //CROSSOVER//
            Config.CROSSOVER.crossover(nextGen);

            this.generations.add(nextGen);

        }
    }

    /**
     * Use roulette wheel selection to determine whether
     * to keep a chromosome in the next generation or not
     * 
     * If the sum is 0 then all chromosomes will be generated
     * again
     * @param c chromosome
     * @param sum sum of fitnesses
     * @return whether to keep the chromosome for next generation
     */
    public boolean keepChromosome(Chromosome c, int sum) {
        if (sum == 0) return false;

        var random = new Random();
        return random.nextDouble() < (c.getFitness()/sum);
    }

    /**
     * Loop through the chromosomes in a generation
     * and calculate their fitness'
     * @param g A generation
     */
    public void setFitnesses(Generation g) {
        var chromosomes = g.getChromosomes();

        for (Chromosome c: chromosomes) {
            c.setFitness(this.fitness(c));
        }
    }

    /**
     * Gets the fitness of a given chromosome
     * The fitness is the sum of the values
     * if the weight is too large then the fitness is 0
     * @param c a chromosome
     * @return the fitness of the chromosome
     */
    public int fitness(Chromosome c) {
        var genes = c.getGenes();

        int fitness = 0;
        int weight = 0;

        for (int i = 0; i < Config.ITEMS.length; i++) {
            if (genes[i]) {
                fitness += Config.ITEMS[i].getValue();
                weight += Config.ITEMS[i].getWeight();

                if (weight > Config.CAPACITY) {
                    return 0;
                }
            }
        }

        c.setWeight(weight);
        return fitness;
    }

    /**
     * Creates an initial random generation
     */
    private void createRandomGeneration() {
        Chromosome[] chromosomes = new Chromosome[Config.CHROMOSOMES_PER_GENERATION];

        for (int i = 0; i < Config.CHROMOSOMES_PER_GENERATION; i++) {
            chromosomes[i] = this.generateRandomChromosome();
        }

        this.generations.add(new Generation(chromosomes));
    }

    /**
     * Generates a chromosome with a random set of genes
     * @return A random chromosome
     */
    private Chromosome generateRandomChromosome() {
        var random = new Random();

        var genes = new boolean[Config.ITEMS.length];
        for (int i = 0; i < Config.ITEMS.length; i++) {
            genes[i] = random.nextInt(100) < 50;
        }

        return new Chromosome(genes);
    }



    public Chromosome getOptimal() {
        return this.optimal;
    }

    public int getOptimalFound() {
        return this.optimalFound;
    }
}
