import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import Genetic.Chromosome;
import Genetic.Generation;
import Genetic.Crossover.Crossover.Crossovers;
import Genetic.Mutation.Mutation.Mutations;

public class Solver {
    //How many chromosomes are in each generation
    private static final int CHROMOSOMES_PER_GENERATION = 5;

    //The max number of generations possible
    private static final int GENERATION_LIMIT = 100;

    //Max capacity of the knapsack
    private static final int CAPACITY = 150;

    //The chosen type of mutation
    private static final Mutations MUTATION = Mutations.RANDOM;

    //The chosen type of crossover
    private static final Crossovers CROSSOVER = Crossovers.SINGLE;

    //MEMBER VARIABLES//

    //The current optimal chromosome
    private Chromosome optimal;
    private int optimalFound;

    //Knapsack capacity
    private final int capacity;

    //The items to put in the knapsack
    private final Item[] items;

    //List of generations
    private ArrayList<Generation> generations = new ArrayList<>();

    public static void main(String[] args) {
        var items = readItemsFromFile("items.csv");

        var solver = new Solver(CAPACITY, items);

        System.out.printf("%d: %d, %d\n", solver.getOptimalFound(), solver.getOptimal().getFitness(), solver.getOptimal().getWeight());
        System.out.println(solver.getOptimal());
    }

    public Solver(int capacity, Item[] items) {
        this.capacity = capacity;
        this.items = items;


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
        while (this.generations.size() <= GENERATION_LIMIT) {
            var generation = this.generations.get(this.generations.size()-1);
            Chromosome[] chromosomes = generation.getChromosomes();

            this.setFitnesses(generation);

            //If a new optimal if found
            if (this.optimal == null || generation.getOptimal().getFitness() > this.optimal.getFitness()) {
                this.optimal = generation.getOptimal();
                this.optimalFound = this.generations.size();
            }

            if (this.generations.size() == GENERATION_LIMIT) break;

            //CREATE NEXT GENERATION            

            int sum = 0;
            for (Chromosome c: chromosomes) {
                sum += c.getFitness();
            }

            var nextGen = new Chromosome[CHROMOSOMES_PER_GENERATION];

            //MUTATION//
            for (int i = 0; i < CHROMOSOMES_PER_GENERATION; i++) {
                nextGen[i] = this.keepChromosome(chromosomes[i], sum) 
                    ? chromosomes[i] 
                    : this.generateRandomChromosome();
                
                //Each chromosome is mutated
                MUTATION.mutate(nextGen[i]);
            }

            //CROSSOVER//
            for (int i = 0; i < CHROMOSOMES_PER_GENERATION-1; i++) {
                CROSSOVER.crossover(nextGen[i], nextGen[i+1]);
            }

            this.generations.add(new Generation(nextGen));

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

        for (int i = 0; i < this.items.length; i++) {
            if (genes[i]) {
                fitness += this.items[i].getValue();
                weight += this.items[i].getWeight();

                if (weight > this.capacity) {
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
        Chromosome[] chromosomes = new Chromosome[CHROMOSOMES_PER_GENERATION];

        for (int i = 0; i < CHROMOSOMES_PER_GENERATION; i++) {
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

        var genes = new boolean[this.items.length];
        for (int i = 0; i < this.items.length; i++) {
            genes[i] = random.nextInt(100) < 50;
        }

        return new Chromosome(genes);
    }

    public static Item[] readItemsFromFile(String filepath) {
        var items = new ArrayList<Item>();

        try {
            var br = new BufferedReader(new FileReader(filepath));

            String line;
            while ((line = br.readLine()) != null) {
                var values = line.split(",");

                items.add(
                    new Item(
                        Integer.parseInt(values[1]),
                        Integer.parseInt(values[0])
                    )
                );
            }

            br.close();

            return items.toArray(new Item[items.size()]);

        } catch (IOException e) {
            System.out.printf("Error reading '%s'\n", filepath);
        }

        return null;
    }

    public Chromosome getOptimal() {
        return this.optimal;
    }

    public int getOptimalFound() {
        return this.optimalFound;
    }
}
