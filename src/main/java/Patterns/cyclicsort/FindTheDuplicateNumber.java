package Patterns.cyclicsort;

public class FindTheDuplicateNumber {

  public static void main(String[] args) {
    getDuplicateNumber(new int[]{1, 4, 4, 3, 2});
    getDuplicateNumber(new int[]{2, 1, 3, 3, 5, 4});

  }

  //number's starts with 1.
  private static void getDuplicateNumber(int[] arr){

    int size = arr.length;
    int i = 0;
    while (i < size){
      if(arr[i] != i+1 && arr[i] - 1 < arr.length){
        if(arr[i] == arr[arr[i] - 1]){
          System.out.println("Dupicate: " + arr[i]);
          break;
        } else {
          exchange(arr, i, arr[i] - 1);
        }
      }else{
        i++;
      }
    }
  }

  private static void exchange(int[] arr, int i, int j){
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }
}
