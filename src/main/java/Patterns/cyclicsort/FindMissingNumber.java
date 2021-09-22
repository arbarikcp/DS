package Patterns.cyclicsort;

public class FindMissingNumber {

  public static void main(String[] args) {

    int missingNumber = getMissingNumber(new int[]{4,0,3,1});
    missingNumber = getMissingNumber(new int[]{0,1,3,4,5});
    missingNumber = getMissingNumber(new int[]{3,5,8,2,4,6,0,1});

  }

  public static int  getMissingNumber(int[] arr ){

    int size = arr.length;
    int  i = 0;
    while (i < size){
      if(i != arr[i] && arr[i] < size){
        exchange(arr,i, arr[i]);
      } else {
        i++;
      }
    }

    for (int j = 0; j < size; j++ ){
      if(j != arr[j]){
        return j;
      }
    }
    return -1;
  }

  private static void exchange(int[] arr, int i, int j){
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

}


