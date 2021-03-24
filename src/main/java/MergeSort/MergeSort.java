package MergeSort;

import ElementarySort.SortUtil;

public class MergeSort {


  public static void bottomUpMergesort(Comparable[] a ){

    int N = a.length;
    Comparable[] aux = new Comparable[N];

    int sz = 2; // minimum array size of the sub arrays to merge.

    for(; sz < N; sz = sz * 2 ) { //Double each time
      for (int lo = 0; lo < N; lo = lo + sz) {
        int hi = Math.min(lo + sz - 1, N - 1);
        int mid = lo + (hi - lo) / 2;
        merge(a, aux, lo, mid, hi);
      }
    }

  }


  public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi){
    //System.out.println("Sort-->" + lo + " -> "+ hi);

    if (hi <= lo) {return;} //Base condition for recursion
    int mid = lo + (hi - lo) / 2;
    sort(a, aux, lo, mid);
    sort(a, aux, mid + 1, hi);
    merge(a, aux, lo, mid, hi);

  }


  //lo is the start index of first array.
  //hi is the last index of the second subarray
  //mid is the last index of first subarray, mid+1 is the start index of next subarray.
  public static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){
    System.out.println("***** Merge -->" + lo + "-> "+mid+"->"+ hi);
    assert isSorted(a, lo, mid);
    assert isSorted(a, mid+1, hi);

    //Copy the elements we are trying to merge to an aux array, So copy lo to hi to an Aux array.
    //Then we will compare in the Aux array and copy back to the main array a
    for (int k = lo; k <= hi; k++ ){
      aux[k] = a[k];
    }

    int i = lo; //start of first sub array, first sub array is from lo to mid
    int j = mid + 1; // start of second sub array. Second sub array is from mid+1, hi
    for (int k = lo; k <= hi; k++) { //Compare the element is aux array, and copy back to a in correct order.
      if (i > mid) { // All elements first sub array is already copied to a
        a[k] = aux[j++]; //Then copy the element from second sub array.
      } else if (j > hi) { //All elements second sub array is already copied to a
        a[k] = aux[i++]; //Then copy the element from first sub array.
      } else if (SortUtil.less(aux[i], aux[j])) {//Compare the elements from first sub array and second sub array.
        a[k] = aux[i++];//If element from first sub array is less then copy the element from first sub array
      } else {
        a[k] = aux[j++];//If element from second sub array is less then copy the element from second sub array
      }
    }

    assert isSorted(a,lo, hi);
  }

  private static boolean isSorted(Comparable[] a, int start, int end){
    for (int i = start + 1; i <= end; i++){
      if(SortUtil.less(a[i], a[i-1])){
        return false;
      }
    }
    return true;
  }

}
