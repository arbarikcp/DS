package Patterns.cyclicsort;

import java.util.ArrayList;
import java.util.List;

public class FindAllMissingNumber {

  public static void main(String[] args) {
    System.out.println(getAllMissingNumber(new int[]{4,3,2,7,8,2,3,1}));
    System.out.println(getAllMissingNumber(new int[]{2, 3, 1, 8, 2, 3, 5, 1}));

  }

  private static List<Integer> getAllMissingNumber(int[] arr){
    List<Integer> duplicates = new ArrayList<>();
    List<Integer> missingNumber = new ArrayList<>();
    int size = arr.length;
    int i = 0, temp = size+1;
    while (i < size){
      int currentNum = arr[i];
      //If the current value is at correct position and current values position is less than total size.
      //We are starting with 1. So oth position will keep element 1.
      if(arr[i] != i+1 && arr[i] - 1 < arr.length){
        if( arr[i] == arr[arr[i] - 1]){ // before exchanging check if both elements are same. Then make that element something bigger.
          duplicates.add(arr[i]); //Keep track of duplicate numbers
          arr[i] = temp++;
        } else {
          exchange(arr, i, arr[i] - 1);
        }
      }
      else{
        i++;
      }
    }

    for(int j = 0; j < size; j++){
      if(arr[j] != j+1){
        missingNumber.add(j+1);//Keep track of missing numbers
      }
    }
    System.out.println(duplicates);
    System.out.println(missingNumber);
    return duplicates;
  }
  private static void exchange(int[] arr, int i, int j){
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

}
