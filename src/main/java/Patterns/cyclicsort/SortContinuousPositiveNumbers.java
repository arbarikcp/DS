package Patterns.cyclicsort;

import java.util.Arrays;

public class SortContinuousPositiveNumbers {

  public static void main(String[] args) {
    sort(new int[]{3,1,5,4,2});

    Arrays.equals(sort(new int[]{3,1,5,4,2,7}), new int[]{1,2,3,4,5,7});


  }

  private  static int[] sort(int[] arr){
    int size = arr.length;
    int  i = 0;
    while (i < size){
      // This while loop can be replaced with an if statement. and i++ can be moved to else block.
      //With moving i++ to else block, we are not incrementing i until our if condition satisfies.
      while (arr[i] != i+1){
        exchange(arr, i,arr[i] -1 );
      }
      i++;
    }
    return arr;
  }


  private  static int[] sort_1(int[] arr){
    int size = arr.length;
    int  i = 0;
    while (i < size){
      if (arr[i] != i+1){
        exchange(arr, i,arr[i] -1 );
      } else {
        i++;
      }
    }
    return arr;
  }

  private static void exchange(int[] arr, int i, int j){
      int temp = arr[i];
      arr[i] = arr[j];
      arr[j] = temp;
  }

}
