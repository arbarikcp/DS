package Patterns.topk;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class Kclosest {

  private static final Comparator<Entry<Integer, Integer>> MAX_HEAP_COMPARATOR =
      (e1, e2) -> e2.getValue() - e1.getValue();
  private static Comparator<Entry<Integer, Integer>> MIN_HEAP_COMPARATOR =
      (e1, e2) -> e1.getValue() - e2.getValue();

  public static void main(String[] args) {
    int arr[] = {2, 4,5, 7, 8, 9};
    // getClosestOrder(arr, 7);

    //getKClosetElement(arr, 7, 2);

    getClosetElementIndex(arr, 10);
  }

  static Map<Integer, Integer> getClosenessMap(int[] arr, int origin) {
    Map<Integer, Integer> closenessMap = new HashMap<>();
    for (int i : arr) {
      closenessMap.put(i, Math.abs(origin - i));
    }
    return closenessMap;
  }

  static void getKClosetElement(int[] arr, int src, int k) {
    // @todo precondition
    Map<Integer, Integer> closenessMap = getClosenessMap(arr, src);

    PriorityQueue<Entry<Integer, Integer>> closenessQ = new PriorityQueue<>(MAX_HEAP_COMPARATOR);
    for (Entry<Integer, Integer> e : closenessMap.entrySet()) {
      int size = closenessQ.size();
      if (size < k) {
        closenessQ.add(e);
      } else if (e.getValue() < closenessQ.peek().getValue()) {
        closenessQ.poll();
        closenessQ.add(e);
      }
    }
    while (!closenessQ.isEmpty()) {
      System.out.println(closenessQ.poll().getKey());
    }
  }

  static void getClosestOrder(int[] arr, int src) {
    // @todo precondition
    Map<Integer, Integer> closenessMap = getClosenessMap(arr, src);

    PriorityQueue<Entry<Integer, Integer>> closenessQ = new PriorityQueue<>(MIN_HEAP_COMPARATOR);
    for (Entry e : closenessMap.entrySet()) {
      closenessQ.add(e);
    }

    while (!closenessQ.isEmpty()) {
      System.out.println(closenessQ.poll().getKey());
    }
  }
// 2, 4, 7, 8, 9

  static int getClosetElementIndex(int[] arr, int x){
    int low = 0;
    int high = arr.length -1;

    int mid = low + (high - low) /2;
    while( low <= high){
      mid = low + (high - low) /2;
      if(arr[mid] == x){
        break;
      }
      else if(arr[mid] < x){
        low = mid + 1;
      }else if( arr[mid] > x){
        high = mid - 1;
      }
    }
    System.out.println(arr[mid]);
    return mid;
  }

}
