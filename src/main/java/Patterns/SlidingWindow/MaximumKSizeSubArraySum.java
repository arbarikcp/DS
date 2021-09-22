package Patterns.SlidingWindow;

public class MaximumKSizeSubArraySum {

  public static void main(String[] args) {
    int[] arr = new int []{2, 3,2,4, 6, 1, 5};
    int k = 3;
    maxSumOfSubArray(arr, k);

    arr = new int []{2, 3,-2,4, -6, 1, 5};
    k = 3;
    maxSumOfSubArrayWithNegativeNumbers(arr, k);
  }

  private static int maxSumOfSubArray(int[] arr, int maxSize){
    assert arr.length >= maxSize;
    int maxSum = Integer.MIN_VALUE;
    int windowStart = 0, windowEnd = 0;
    int subArraySum = 0;
    int start =0, end =0;
    for(windowEnd = 0; windowEnd < arr.length; windowEnd++){
      int subArraySize = windowEnd - windowStart + 1;
      if(subArraySize <= maxSize){
        subArraySum += arr[windowEnd];
      }else{
        //Remove the windowStart element, slide the windowsStart, add the new windowEnd element to the sum
        subArraySum -= arr[windowStart++];
        subArraySum +=  arr[windowEnd];
      }

      //maxSum = Math.max(maxSum,subArraySum); // if we just need maxSum, or if we need the position then we need to keep the start and end
      if(subArraySum > maxSum){
        maxSum = subArraySum;
        start = windowStart;
        end = windowEnd;
      }
    }
    System.out.println("maxSum="+ maxSum+", start="+ start+" , end="+ end);
    return maxSum;
  }


  private static int maxSumOfSubArrayWithNegativeNumbers(int[] arr, int maxSize){
    assert arr.length >= maxSize;
    int maxSum = Integer.MIN_VALUE;
    int windowStart = 0, windowEnd = 0;
    int subArraySum = 0;
    int start = 0, end = 0;
    for(windowEnd = 0; windowEnd < arr.length; windowEnd++){
      int subArraySize = windowEnd - windowStart + 1;
      if(subArraySize <= maxSize){
        subArraySum += arr[windowEnd];
        maxSum = subArraySum;
      }else{
        //Remove the windowStart element, slide the windowsStart, add the new windowEnd element to the sum
        subArraySum -= arr[windowStart++];
        subArraySum +=  arr[windowEnd];
        //maxSum = Math.max(maxSum,subArraySum); // if we just need maxSum, or if we need the position then we need to keep the start and end
        if(subArraySum > maxSum){
          maxSum = subArraySum;
          start = windowStart;
          end = windowEnd;
        }
      }
    }
    System.out.println("maxSum="+ maxSum+", start="+ start+" , end="+ end);
    return maxSum;
  }

}
