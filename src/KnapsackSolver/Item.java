package KnapsackSolver;

public final class Item {
    //The item's value
    private final int value;

    //The item's weigth
    private final int weight;

    /**
     * Creates a new item
     * @param value
     * @param weight
     */
    public Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
    }

    //GETTERS//

    public int getValue() {
        return this.value;
    }

    public int getWeight() {
        return this.weight;
    }
}