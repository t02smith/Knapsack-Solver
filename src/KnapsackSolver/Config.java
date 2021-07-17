package KnapsackSolver;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import KnapsackSolver.Genetic.Crossover.Crossover.Crossovers;
import KnapsackSolver.Genetic.Fitness.FitnessAlg.Fitness;
import KnapsackSolver.Genetic.Mutation.Mutation.Mutations;

public abstract class Config {
    //How many chromosomes are in each generation
    public static final int CHROMOSOMES_PER_GENERATION = 10;

    //The max number of generations possible
    public static final int GENERATION_LIMIT = 100;

    //Max capacity of the knapsack
    public static final int CAPACITY = 150;

    //A target value to quit if reached/exceeded
    public static final int TARGET = 1000;

    //The items being put in the knapsack
    public static final Item[] ITEMS = readItemsFromFile("items.csv");

    //The chosen fitness algorithm
    public static final Fitness FITNESS = Fitness.PRIORITY;

    //The chosen type of mutation
    public static final Mutations MUTATION = Mutations.DOUBLE_MUTATION;

    //The chosen type of crossover
    public static final Crossovers CROSSOVER = Crossovers.SINGLE;

    /**
     * Reads a list of items from a file
     * @param filepath The location of the file
     * @return
     */
    public static Item[] readItemsFromFile(String filepath) {
        var items = new ArrayList<Item>();

        try {
            var br = new BufferedReader(new FileReader(filepath));

            String line;
            while ((line = br.readLine()) != null) {
                var values = line.split(",");

                //Item (value, weight)
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
}
