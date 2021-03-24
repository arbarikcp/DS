package MergeSort;

public class mergeSortClient {

  public static void main(String[] args) {
   Integer[] a = {12, 34, 54, 67,21, 67, 89, 99,31,8};
   Integer[] aux = new Integer[10];
   //MergeSort.merge(a, aux, 0,0,1);

/*    for(int i = 0 ; i< a.length; i++){
      aux[i] = a[i];
    }*/

    //MergeSort.sort(a,aux, 0,9);
    MergeSort.bottomUpMergesort(a);


  }
}
