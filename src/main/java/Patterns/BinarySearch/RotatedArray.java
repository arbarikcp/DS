package Patterns.BinarySearch;

public class RotatedArray {

  public static void main(String[] args) {
    int[] arr =new int[]{18,3,7,8,9,10, 15};
    System.out.println(findMin(arr));
  }

  public static int findMin(int[] arr){
    int start = 0, end = arr.length -1;
    int mid = start + (end - start) /2;
    while (start < end){
      mid = start + (end - start) /2;
      if ( (mid < end && arr[mid] > arr[mid+1])){
        return mid + 1;
      }
      if(mid > start && arr[mid-1] < arr[mid]){
        return mid;
      }

      if(arr[start] < arr[mid]){
        start = mid +1;
      }else{
        end = mid -1;
      }
    }
    return 0;
  }

}
