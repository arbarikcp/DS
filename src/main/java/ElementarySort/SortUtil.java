package ElementarySort;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Comparator;

public class SortUtil {

  public static boolean less(Comparable i, Comparable j) {
    //System.out.println("Comparing " + i + "," + j);
    return i.compareTo(j) < 0;
  }

public static boolean less(Object i, Object j, Comparator c ){
    return c.compare(i, j) < 0;
}

  public static boolean greater(Comparable i, Comparable j) {
    //System.out.println("Comparing " + i + "," + j);
    return i.compareTo(j) > 0;
  }

  public static boolean greater(Object i, Object j, Comparator c ){
    return c.compare(i, j) > 0;
  }




  public static void exchange(Comparable[] a, int i, int j) {
    Comparable item = a[i];
    a[i] = a[j];
    a[j] = item;
    System.out.println("      Exchanged " + i + " -> " + j);
  }

  public static void exchange(Object[] a, int i, int j) {
    Object item = a[i];
    a[i] = a[j];
    a[j] = item;
    System.out.println("      Exchanged " + i + " -> " + j);
  }

  public static int getMinimum(Comparable[] a, int start, int end) {
    int minIndex = start;
    for (int i = start; i < end; i++) {
      if (less(a[i], a[minIndex])) {
        minIndex = i;
      }
    }
    return minIndex;
  }

  public static int getMinimum(Object[] a,Comparator c, int start, int end) {
    int minIndex = start;
    for (int i = start; i < end; i++) {
      if (less(a[i], a[minIndex],c)) {
        minIndex = i;
      }
    }
    System.out.println("minimum from "+ start + " to end "+ end + " is "+ a[minIndex]);
    return minIndex;
  }

  public static int getMaximum(Comparable[] a, int start, int end) {
    int maxIndex = start;
    for (int i = start; i < end; i++) {
      if (less(a[maxIndex], a[i])) {
        maxIndex = i;
      }
    }
    return maxIndex;
  }

  public static void shuffle(Comparable[] a){
    try {
      int max = a.length -1;
      SecureRandom sr = SecureRandom.getInstanceStrong();
      for(int i = 0; i <= max; i++){
        int randomPosition = sr.nextInt(max - i + 1) + i;
        SortUtil.exchange(a, i, randomPosition);
      }
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
  }

  public static boolean isSorted(Object[] a, Comparator c){
    int n = a.length;
    for(int i = 0 ; i < n-1 ; i++){
      if(SortUtil.greater(a[i],a[i+1],c)){
        System.out.println("element at index "+ i +" is greater than element at "+ (i+1) );
        System.out.println("element at index "+ i +"="+ a[i]);
        System.out.println("element at index "+ (i+1) +"="+ a[i+1]);
        return false;
      }
    }
    return true;
  }

  public static boolean isSorted(Comparable[] a){
    int n = a.length;
    for(int i = 0 ; i < n-1 ; i++){
      if(SortUtil.greater(a[i], a[i+1])){
        System.out.println("element at index "+ i +" is greater than element at "+ (i+1) );
        System.out.println("element at index "+ i +"="+ a[i]);
        System.out.println("element at index "+ (i+1) +"="+ a[i+1]);
        return false;
      }
    }
    return true;
  }

}
