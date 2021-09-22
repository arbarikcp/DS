package DP;

public class CanPartition {

  int[] num = {};

  public CanPartition(int[] num) {
    this.num = num;
  }

  public boolean isEqualPartitionPossible(){
    int totalSum = getTotalSum();
    if(totalSum % 2 != 0){
      return false;
    }else{
      return isEqualPartitionPossible(totalSum/2, 0);
    }
  }

  private boolean isEqualPartitionPossible( int targetSum, int currentIndex ){
    if(targetSum == 0){
      return true;
    }
    if(currentIndex >= num.length){
      return false;
    }

    if(targetSum >= num[currentIndex]){
       if(isEqualPartitionPossible(targetSum - num[currentIndex], currentIndex+1)){
         return true;
       }
    }

    return isEqualPartitionPossible(targetSum,currentIndex +1);

  }

  private int getTotalSum(){
    int totalSum = 0;
    for (int i = 0; i < num.length; i++){
      totalSum += num[i];
    }
    return totalSum;
  }

  public static void main(String[] args) {
    int[] num = {1, 2, 3, 4};
    CanPartition cp = new CanPartition(num);
    System.out.println(cp.isEqualPartitionPossible());
  }
}
