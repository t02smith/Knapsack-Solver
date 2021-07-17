package KnapsackSolver;

import KnapsackSolver.Genetic.OptimalChromosome;

public abstract class Main {

    //How many times to run the algorithm
    private static final int ITERATIONS = 5;
    
    public static void main(String[] args) {
        int generationSum = 0;
        int valueSum = 0;
        int weightSum = 0;

        double totalTime = 0;
        double startTime;

        double allIterationsTime = System.currentTimeMillis();
        for (int i = 1; i <= ITERATIONS; i++) {
            startTime = System.currentTimeMillis();
            var attempt = runSolver();
            totalTime += (System.currentTimeMillis()-startTime);

            generationSum += attempt.getGenerationFound();
            valueSum += attempt.getValue();
            weightSum += attempt.getWeight();
        }

        allIterationsTime = System.currentTimeMillis() - allIterationsTime;

        System.out.printf("Average chromosome value: %.2f\n", (double)valueSum/ITERATIONS);
        System.out.printf("Average chromosome weight: %.2f\n", (double)weightSum/ITERATIONS);
        System.out.printf("Average optimal found at generation: %.2f\n", (double)generationSum/ITERATIONS);
        System.out.printf("Average time taken %.2f ms\n", (double)totalTime/ITERATIONS);
        System.out.printf("Total time taken %.2f ms\n", allIterationsTime);
    }

    /**
     * Runs a solver
     * @return the optimal chromosome from that instance
     */
    public static OptimalChromosome runSolver() {
        return new Solver().getOptimal();
    }
}
