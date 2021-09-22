package Patterns.TwoPointers;

import java.util.Arrays;

public class RemoveDuplicates {

  public static void main(String[] args) {
    int[] arr = new int[]{2,3,3,3,3,6,6,6,6,9}; // Array is Sorted. Without this below techniques will not work.
    printUniqueElements(arr);
    removeDuplicates(arr);
  }

  //First element is always unique.Because we have not seen other elements yet.
  //Then for each element look backward to see if it is a duplicate.
  public static void printUniqueElements(int[] arr){
    System.out.print(arr[0]+","); //print the first element, its always unique because we have not seen other element yet.
    for(int i = 1; i < arr.length ; i++){
      if( arr[i-1] == arr[i]){
        continue;
      }else{
        System.out.print(arr[i]+",");
      }
    }
  }

  //Similar to printUniqueElements.
  //One pointer moves through the elements, determines which are unique elements.
  //Second pointer points to the element which are duplicate and need to be filled by the unique elements.
  //
  public static void removeDuplicates(int[] arr){
    int nextUniqueElementPosition = 1;
    for(int i = 1; i < arr.length ; i++){
      if( arr[i-1] == arr[i]){
        continue;
      }else{
        arr[nextUniqueElementPosition++] = arr[i];
      }
    }
    for(int i =0; i< nextUniqueElementPosition; i++){
      System.out.print(arr[i]+",");
    }
  }


}
