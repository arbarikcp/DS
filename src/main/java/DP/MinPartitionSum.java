package DP;

import java.util.HashSet;
import java.util.Set;

public class MinPartitionSum {

  int[] num;

  public MinPartitionSum(int[] num) {
    this.num = num;
  }

  public int getMinPartitionDifference(){
    return minPartitionDifference(0, 0,0);
  }

  private int minPartitionDifference(int currentIndex, int partitionSum1, int partitionSum2){
    if(currentIndex == num.length){
      return Math.abs(partitionSum1 - partitionSum2);
    }

    int diff1 = minPartitionDifference(currentIndex +1,
        partitionSum1 + num[currentIndex], partitionSum2);

    int diff2 = minPartitionDifference(currentIndex+1,
        partitionSum1, partitionSum2+ num[currentIndex]);

    return Math.min(diff1, diff2);

  }


  //Wrong approach .... why ?
  private int getMinPartitionDifference_1(){
    Set<Integer> firstSet = new HashSet<>();
    Set<Integer> secondSet = new HashSet<>();
    int firstSetSum = num[0], secondSetSum = 0;
    firstSet.add(num[0]);
    for(int i = 1; i < num.length; i++){
      int includeWithFirstSet = firstSetSum + num[i];
      int includeWithSecondSet = secondSetSum + num[i];

      int diff1 = Math.abs(includeWithFirstSet - secondSetSum);
      int diff2 = Math.abs(includeWithSecondSet - firstSetSum);

      if(diff1 < diff2){
        firstSetSum += num[i];
        firstSet.add(num[i]);
      }else{
        secondSetSum += num[i];
        secondSet.add(num[i]);
      }
    }

    System.out.println("Set1 ="+ firstSet);
    System.out.println("Set2 = "+ secondSet);
    return Math.abs(firstSetSum - secondSetSum);
  }


  public static void main(String[] args) {
    int[] num = {1, 2, 3, 9};
    MinPartitionSum mp = new MinPartitionSum(num);
    // System.out.println(mp.getMinPartitionDifference());
    System.out.println(mp.getMinPartitionDifference_1());
  }
}
