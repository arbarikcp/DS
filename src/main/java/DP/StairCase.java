package DP;

public class StairCase {

  public static int countWays(int n){

    if(n < 0){
      return 0;
    }
    if(n == 0){
      return 1;
    }
    if(n == 1){
      return 1;
    }

    return countWays(n-1) + countWays(n-2) + countWays(n-3);

  }

  public static void main(String[] args) {
    System.out.println(StairCase.countWays(4));
  }
}
