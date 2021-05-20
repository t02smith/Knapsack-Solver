package KnapsackSolver;

public abstract class Main {
    private static final int TARGET = 400;

    private static final int ITERATIONS = 1000;

    public static void main(String[] args) {
        int successes = 0;
        
        int generationSum = 0;
        int targetHit = 0;
        
        for (int attempts = 1; attempts <= ITERATIONS; attempts++) {
            var attempt = runSolver();

            if (attempt != -1) {
                successes++;
                generationSum += attempt;
                targetHit++;
            }
        }

        double successRate = 100*successes/ITERATIONS;
        int avgGen = generationSum/targetHit;

        System.out.printf("Target Value Hit: %.2f%%\n", successRate);
        System.out.printf("Average Generation for Maximum: %d\n", avgGen);
    }

    /**
     * Runs a solver
     * @return How many generations it took to hit the target value
     *         0 if it did not hit the target value
     */
    public static int runSolver() {
        var solver = new Solver();

        if (solver.getOptimal().getValue() != TARGET) return -1;
        else return solver.getOptimalFound();
    }
}
