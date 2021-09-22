package Interview;

import java.util.Arrays;

public class Partition {

  public static void main(String[] args) {
    int[] arr = new int[] {4,8,5,2,2,6,2,3,9};
    int[] partitioned = twoWayPartition(arr);
    System.out.println(Arrays.toString(partitioned));

    Integer[] arr1 = new Integer[]{4,8,5,2,2,6,2,3,9};
    Integer[] pArr1 = threeWayPartitioning(arr1);
    System.out.println(Arrays.toString(pArr1));
  }

  private static int[] twoWayPartition(int[] arr){

    int pivot = arr[0];
    int i = 0;
    int j = arr.length -1;
    while (true){

      while (i < arr.length && arr[i] <= pivot) i++;
      while (j > 0 && arr[j] > pivot) j--;

      if(i > j) break;
      swap(arr, i, j);
    }
    swap(arr, 0, j);
    return arr;
  }


  private static Integer[] threeWayPartitioning(Integer[] arr){
    int i = 0;
    int lt = 0;
    int gt = arr.length -1;
    Integer pivot = arr[0];

    while (i<=gt){
      int cmp = arr[i].compareTo(pivot);
      if(cmp < 0) {swap(arr,i,lt ); i++; lt++;}
      if(cmp > 0) {swap(arr,i,gt); gt--;}
      if(cmp == 0) i++;
      }
    return arr;
  }

  private static void swap(int[] arr, int i, int j){
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  private static void swap(Integer[] arr, int i, int j){
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }
}
