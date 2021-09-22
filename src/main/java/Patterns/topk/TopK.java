package Patterns.topk;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class TopK {

  // use a heap of K elements.
  // For every incoming element, we need to check if that element has a place in our heap.
  // If incoming element is greater than the any element of the heap then it has a place in heap.
  //So we need to compare the min element of the heap with the incoming element.
  //MinHeap

  //static Comparator<Integer> minHeapComparator = (n1, n2) -> n1 - n2;
  static Comparator<Integer> maxHeapComparator = (n1, n2) -> n2 - n1;

  public static void main(String[] args)
  {
    int[] num =  { 3, 1, 5, 12, 2, 11 };
    int k = 20;
    PriorityQueue<Integer> minHeap = new PriorityQueue();

    for(int i = 0; i< num.length; i++){
      int size = minHeap.size();
      if(size < k){
        minHeap.add(num[i]);
      }else if(minHeap.peek() < num[i]){
        minHeap.poll();
        minHeap.add(num[i]);
      }
    }


    while (minHeap.peek() != null){
      System.out.print(minHeap.poll()+",");
    }

    //System.out.println("completed");
  }


}
