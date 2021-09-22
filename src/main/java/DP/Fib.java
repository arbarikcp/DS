package DP;

import java.util.HashMap;
import java.util.Map;

public class Fib {

  private Map<Integer, Integer> memo = new HashMap<>();

  public static void main(String[] args) {
    System.out.println(new Fib().getFib(40));
  }

  int getFib(int num) {
    if (memo.containsKey(num)) {
      return memo.get(num);
    }
    if (num < 2) {
      return 1;
    }
    int result = getFib(num - 2) + getFib(num - 1);
    memo.put(num,result);
    return result;
  }
}
