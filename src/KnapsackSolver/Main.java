package KnapsackSolver;

import KnapsackSolver.Genetic.OptimalChromosome;

public abstract class Main {

    private static final int ITERATIONS = 1000;

    
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

    /*
    public static void main(String[] args) {
        int successes = 0;
        
        int generationSum = 0;
        int targetHit = 0;

        double totalTime = 0;
        double startTime;
        
        for (int attempts = 1; attempts <= ITERATIONS; attempts++) {
            startTime = System.currentTimeMillis();
            var attempt = runSolver();
            totalTime += (System.currentTimeMillis()-startTime);

            if (attempt.getValue() >= Config.TARGET) {
                successes++;
                generationSum += attempt.getGenerationFound();
                targetHit++;
            }
        }

        double successRate = 100*successes/ITERATIONS;
        System.out.printf("Target Value Hit: %.2f%%\n", successRate);
        
        double avgTime = totalTime/ITERATIONS;
        int avgGen = generationSum/targetHit;
        System.out.printf("Average Generation for Maximum: %d\n", avgGen);
        System.out.printf("Average Time Taken: %.2fms\n", avgTime);
    }*/

    /**
     * Runs a solver
     * @return the optimal chromosome from that instance
     */
    public static OptimalChromosome runSolver() {
        return new Solver().getOptimal();
    }
}
