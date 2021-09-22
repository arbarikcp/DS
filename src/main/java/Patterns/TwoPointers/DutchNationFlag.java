package Patterns.TwoPointers;

import java.util.Arrays;

public class DutchNationFlag {

  public static void main(String[] args) {
    int[] arr = {0,1,2,1,1,2,2,0,0,1,0};
    sort(arr);
  }

  private static  void sort(int[] arr){
    int low = 0, high = arr.length -1;
    for(int i = 0; i<= high;){
      if(arr[i] == 0){
        swap(arr, i, low);
        low++;
        i++;
      }else if(arr[i] == 2){
        swap(arr, i, high);
        high --;
      }else{
        i++;
      }
    }
    System.out.println(Arrays.toString(arr));
  }

  static void swap(int[] arr, int sIndex, int tIndex){
    int temp = arr[sIndex];
    arr[sIndex] = arr[tIndex];
    arr[tIndex] = temp;
  }

}
