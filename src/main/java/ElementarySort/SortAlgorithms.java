package ElementarySort;

import java.util.Arrays;
import java.util.Comparator;

public class SortAlgorithms {

  public static void selectionSort(Comparable a[]) {

    int n = a.length;
    for (int i = 0; i < n - 1; i++) {
      int minIndex = i;

      // Find the min Element Index from ith to (n-1)th element
      // : For each i,  Find the minimum among remaining elements.
      //for i = 0, you need n comparison to find minimum from 0 to n-1.
      //: n + (n-1) + (n-2) + ...+1 comparison -> total comparison is (n^2) / 2
      for (int j = i; j < n; j++) {
        if (SortUtil.less(a[j], a[minIndex])) {
          minIndex = j;
        }
      }

      //Exchange if a[i] is not the minimum, we have minIndex different from i
      //For already fully sorted array, it will not exchange anything.
      //But no much advantage on Partial sorted array, as it will exchange ith element with minElelement( moves a lot of position without checking between elements)
      if (i != minIndex) {
        SortUtil.exchange(a, i, minIndex);
      }
    }
  }


  public static void  insertionSort(Comparable[] a){
    int n = a.length;
    for (int i = 1; i < n; i++ ){
      for (int j = i; j > 0 && SortUtil.less(a[j], a[j-1]); j--){
          SortUtil.exchange(a, j, j-1);
      }
    }
  }

  public static void shellSort(Comparable[] a){

    //Best known increment sequence 3n + 1 -> ((n+1) increment sequence is 3n +1 )
    // n = 0, sequence length = 1
    //n = 1, Sequence length = 4
    //n = 4, sequence length = 13
    //n = 5, sequence length = 40

    int n = a.length;
    int h = 1;
    while(h < n/3 ) h =  3 * h + 1;

    while (h >= 1){ //we need to run until We have sequence length is 1.
      System.out.println("h value ="+h);
      for(int i = h; i < n ; i++){ // Do an Insertion sort on every hth element sub array. At end of this we will have an array, whose every hth element is sorted.
        for(int j = i; j >= h && SortUtil.less(a[j], a[j-h]); j = j-h ){
          SortUtil.exchange(a, j, j-h);
        }
      }
      h = h/3;
    }
  }


  public static void selectionSortWithComparator(Object a[], Comparator c) {
    int n = a.length;
    for(int i = 0; i < n ; i++){
      int minIndex = SortUtil.getMinimum(a, c, i, n);
      if(minIndex != i){
        SortUtil.exchange(a, i,minIndex);
      }
      System.out.println("After "+ i +" iteration: "+ Arrays.toString(a));
    }
  }

/*
  public static void insertionSortWithComparator_exp(Object[] a, Comparator c){
    int n = a.length;
    for(int i = 0; i < n; i++){
      for( int j = i; j > 0 && SortUtil.less(a[j],a[j-1],c); j--){
        SortUtil.exchange(a, i, j); // Exchnage with J and j -1 not with i.
      }
    }
  }
*/


  public static void insertionSortWithComparator(Object[] a, Comparator c){
    int n = a.length;
    for(int i = 1; i < n ; i++){
      for( int j = i; j > 0 ;j--){
        if(SortUtil.less(a[j],a[j-1],c)) {
          SortUtil.exchange(a, j, j-1);
        }
      }
    }
  }



}
