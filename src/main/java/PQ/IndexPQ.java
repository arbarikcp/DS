package PQ;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import java.util.Arrays;

public class IndexPQ {

  private Comparable[] objects;
  private int[] heap;
  private int[] positionInHeap;
  int maxN;
  int size;
  int count;
  private BiMap<String, Integer> keyMap;

  public IndexPQ(Comparable[] objects) {
    this.objects = objects;
    heap = new int[objects.length];
    maxN = objects.length;
    size = 0;
    positionInHeap = new int[objects.length];
    Arrays.fill(positionInHeap, -1);
    keyMap = HashBiMap.create();
    for(int i = 1; i< objects.length; i++){
      keyMap.put((String) objects[i], i);
      add(i);
    }
  }


  private void add(int index){
    heap[++size] = index;
    positionInHeap[size] = size;
    swim(size);
  }

  private Object poll(){
    Object current = objects[heap[1]];
    heap[1] = heap[size];
    heap[size] = -1;
    size--; // decrease the total elements in heap
    sink(1);
    return current;
   }


   private void add(String key){
    keyMap.put(key, ++size);
    heap[size] = size;
    positionInHeap[size] = size;
    swim(size);
   }

   private void remove(String key){
    int keyIndex = keyMap.get(key);
    keyMap.remove(key);
    Integer posInHeap = positionInHeap[keyIndex];
    heap[posInHeap] = heap[size--];
    sink(posInHeap);
   }


   private boolean isEmpty(){
    return size == 0;
   }

  private void swim(int i){
    while (i > 1 && less(i/2, i)){
      exchange(i, i/2);
      i = i/2;
    }
  }

  private void sink(int i){

    while (2 * i <= size){
      int j = 2 * i;
      if(j < size && less(j, j+1)) j++;
      if(!less(i, j)) break;
      exchange(i, j);
      i = j;
    }
  }


  private void exchange(int i, int j){
    int temp = heap[i];
    heap[i] = heap[j];
    heap[j] = temp;

    int pTemp = positionInHeap[heap[i]];
    positionInHeap[heap[i]] = positionInHeap[heap[j]];
    positionInHeap[heap[j]] = pTemp;
  }

  private boolean less(int i, int j){
    return objects[heap[i]].compareTo(objects[heap[j]]) < 0;
  }

  private int[] getPriorityOrder(){
    return positionInHeap;
  }

  public static void main(String[] args) {
    String[] strings = {"","hello","queen", "run", "my", "aa", "world", "this", "is", "a", "good", "day", "zorro", "superman"};
    IndexPQ ipq = new IndexPQ(strings);
    System.out.println(Arrays.toString(ipq.getPriorityOrder()));

    ipq.remove("run");
    ipq.remove("day");
    //ipq.add("ola");
    //ipq.add("uber");


    while (!ipq.isEmpty()){
      System.out.println(ipq.poll());
    }
  }
}
