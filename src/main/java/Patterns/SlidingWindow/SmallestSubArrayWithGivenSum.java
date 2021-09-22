package Patterns.SlidingWindow;

import java.util.Hashtable;

public class SmallestSubArrayWithGivenSum {

  public static void main(String[] args) {
    int[] arr =  new int[]{2, 1, 5, 2, 3, 2,7};
    int s = 7;
    getSmallestSubArrayWithSum(arr,s);
  }

  private static void getSmallestSubArrayWithSum(int[] arr, int requiredSum){

    //Initialize all the answer variable to default value:

    //For example, if we want Smallest subarray, then initialize it is MAX_Value,
    //So that first match will be smaller than the default.

    //For example, If we want longest subarray,
    //then initialize it to MIN_Value, So that first match will be longer than the default.

    //If we want array positions to be returned,
    //then initialize Start and end to invalid array Index (-1), not 0. because if there is nom match, it will return -1.

    int size = Integer.MAX_VALUE;
    int start = -1, end = -1;
    int windowStart = 0, windowEnd = 0;
    int currentSum = 0;
    for(windowEnd = 0; windowEnd < arr.length; windowEnd++){

      currentSum += arr[windowEnd];
      if(currentSum > requiredSum){
        //shrink the window from start, until it is greater than requiredsum
        while(windowStart <= windowEnd && currentSum > requiredSum){
          currentSum -= arr[windowStart++];
        }
      }
      if(currentSum == requiredSum){
        int subArraySize = windowEnd - windowStart + 1;
        if(size > subArraySize){
          size = subArraySize;
          start = windowStart;
          end = windowEnd;
        }
      }
    }
    System.out.println("size="+ size + ", start="+ start+", end="+ end);
  }

}
