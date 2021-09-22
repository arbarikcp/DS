package DP;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sum {

  // arr has +ve numbers, We can take any number multiple time .

  public static void main(String[] args) {
/*    System.out.println(new Sum().canSum(7, new int[] {5, 3, 4, 7}));
    System.out.println(new Sum().canSum(300, new int[] {7, 14}));*/


    //System.out.println(new Sum().howSum(7, new int[] {5, 3, 4, 7}));
    System.out.println(new Sum().howSum(8, new int[] {2,5, 3, 4, 7}));

    System.out.println(new Sum().bestSum(7, new int[] {5, 3, 4, 7}));
    System.out.println(new Sum().bestSum(8, new int[] {2,5, 6, 4, 7}));

  }

  public boolean canSum(int target, int[] arr) {

    HashMap<Integer, Boolean> memo = new HashMap<>();
    return canSum_Internal(target, arr, memo);
  }

  private boolean canSum_Internal(int target, int[] arr, Map<Integer, Boolean> memo) {

    if (memo.containsKey(target)) {
      return memo.get(target);
    }
    // Base condition, we reached at solution
    if (target == 0) {
      return true;
    }
    // Base condition, We don't have a solution
    if (target < 0) {
      return false;
    }

    for (int i = 0; i < arr.length; i++) {
      int reminder = target - arr[i];
      if (canSum_Internal(reminder, arr, memo)) {
        memo.put(target, true);
        return true;
      }
    }

    memo.put(target, false);
    return false;
  }

  public List<Integer> howSum(int target, int[] arr){
    Map<Integer, List<Integer>> memo = new HashMap<>();
    return howSum(target, arr, memo);
  }

  private List<Integer> howSum(int target, int[] arr,Map<Integer, List<Integer>> memo){

    if(memo.containsKey(target)){
      return memo.get(target);
    }
    if(target == 0) {
      return new ArrayList<>();
    }
    if(target < 0){
      return null;
    }

    for(int i = 0; i < arr.length ; i++){
      int reminder = target - arr[i];
      List<Integer> result = howSum(reminder, arr, memo);
      if(result != null){
        result.add(arr[i]);
        memo.put(target, result);
        return memo.get(target);
      }
    }
    memo.put(target, null);
    return null;
  }

  public List<Integer> bestSum(int target, int[] arr){
    Map<Integer, List<Integer>> memo = new HashMap<>();
    return bestSum(target, arr, memo);
  }

  private List<Integer> bestSum(int target, int[] arr, Map<Integer, List<Integer>> memo ){

    if(memo.containsKey(target)){
      return memo.get(target);
    }
    if(target == 0){
      return new ArrayList<>();
    }
    if(target < 0){
      return null;
    }

    List<Integer> bestResult = null;
    for(int i = 0; i < arr.length; i++){
      int reminder = target - arr[i];
      List<Integer> result = bestSum(reminder, arr, memo);
      if(result != null){
        result.add(arr[i]);
        if(bestResult == null || bestResult.size() > result.size()){
          bestResult = result;
        }
      }
    }
    memo.put(target, bestResult);
    return bestResult;
  }


}
