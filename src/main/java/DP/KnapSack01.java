package DP;

public class KnapSack01 {

  int[] weights;
  int[] profits;
  int size;

  public KnapSack01(int[] weights, int[] profits) {
    this.weights = weights;
    this.profits = profits;
    this.size = weights.length;
  }

  public int getMaxProfit(int capacity) {
    return getMaxProfit(0, capacity);
  }

  private int getMaxProfit(int currentIndex, int currentCapacity) {

    if (currentIndex >= size || currentCapacity <= 0) {
      return 0;
    }

    int profit1 = 0; // if we take the current item
    if (weights[currentIndex] <= currentCapacity) { // We can take current item
      profit1 = profits[currentIndex] +
          getMaxProfit(currentIndex + 1, currentCapacity - weights[currentIndex]);
    }

    int profit2 = getMaxProfit(currentIndex + 1, currentCapacity); // don't take the current item

    return Math.max(profit1, profit2);
  }

  public static void main(String[] args) {
    int[] profits = {1, 6, 10, 16};
    int[] weights = {1, 2, 3, 5};
    int capacity = 7;
    KnapSack01 kp = new KnapSack01(weights,profits);
    System.out.println(kp.getMaxProfit(capacity));
  }
}
