package Patterns.TwoPointers;

import java.util.Arrays;
import java.util.TreeMap;

public class SquaringSortedArray {

  public static void main(String[] args) {
    int[] arr = {-9,-5, -2, -1,0, 2, 3, 8,9,12,13,15};
    //System.out.println(getFirstNonNegativeNumberIndex(arr));
    int[] square = getSortedSquaredArray(arr);
    System.out.println(Arrays.toString(square));

  }

  private static int[] getSortedSquaredArray(int[] arr){

    int[] square = new int[arr.length];
    int left = 0, right = arr.length -1;
    for(int i = 0; i< arr.length; i++){
      int lSquare = (int) Math.pow(arr[left],2);
      int rSquare = (int) Math.pow(arr[right],2);
      if(lSquare > rSquare){
        square[i] = lSquare;
        left++;
      }else{
        square[i] = rSquare;
        right--;
      }
    }
    return  square;
  }

  private static int getFirstNonNegativeNumberIndex(int[] arr){
    int low = 0, high = arr.length -1;
    int mid = 0;
    while (low <= high){
      mid = low + (high -low) /2;
      if(arr[mid] == 0){
        break;
      } else if(arr[mid] > 0){
        high = mid -1;
      }else{
        low = mid +1;
      }
    }
    while (arr[mid] < 0 && mid < arr.length) {
      mid = mid+1;
    }
    return mid;
  }
}
