package PQ;

import java.util.Objects;

public class PQ<T> {

  T[] objects;
  int arrSize;
  int count;
  boolean minPQ = false;

  public PQ(boolean minPQ) {
    this.objects = (T[]) new Object[20];
    arrSize = 20;
    count = 0;
    objects[0] = null;
    this.minPQ = minPQ;
  }

  public void add(T newObject){
    objects[++count] = newObject;
    swim_(count);
  }

  public T peek(){
    return objects[0];
  }

  public boolean isEmpty(){
    return count < 1;
  }

  public T poll(){
    T currentObj = objects[1];
    swap(1, count);
    objects[count] = null;
    count --;
    sink_(1);
    return currentObj;
  }



  private void swim(int i){
    int current = i;
    int parent = current /2;
    while(true){
      if(parent > 0 && less(parent, current)){
        swap(parent, current);
        current = parent;
        parent = parent /2;
      }else{
        break;
      }
    }
/*    for(int current = i, parent = i/2; parent > 0 && less(parent, current);parent = current, parent = parent /2){
      swap(current, parent);
    }*/
  }

  private void sink(int i){
    int current  = i;
    while (true) {
      int leftChild = current * 2;
      int rightChild = leftChild + 1;
      int maxElementIndex = current;
      if (leftChild <= count && less(current, leftChild)) {
        maxElementIndex = leftChild;
      }
      if (rightChild <= count && less(leftChild, rightChild)) {
        maxElementIndex = rightChild;
      }
      if (maxElementIndex != current) {
        swap(current, maxElementIndex);
        current = maxElementIndex;
      } else {
        break;
      }
    }
  }


  private void sink_(int k){
    while ( 2 * k <= count){
      int j = 2 * k;
      if(j < count && isExchangeNeeded(j, j+1,minPQ)) j++;
      if(!isExchangeNeeded(k, j,minPQ)) break;
      swap(k, j);
      k = j;
    }
  }

  private void swim_(int k){
    while (k > 1 && isExchangeNeeded(k/2, k, minPQ)){
      swap(k, k/2);
      k= k/2;
    }
  }

  private void swap(int i, int j){
    T temp =  objects[i];
    objects[i] = objects[j];
    objects[j] = temp;
  }

  private boolean isExchangeNeeded(int i, int j, boolean minPq){
    if(minPq){
      return greater(i, j);
    }else{
      return less(i, j);
    }
  }

  private boolean greater(int i, int j){
    if( ((Comparable) objects[i]).compareTo((Comparable)objects[j]) > 0){
      return true;
    }else{
      return false;
    }
  }

  private boolean less(int i, int j){

     if( ((Comparable) objects[i]).compareTo((Comparable)objects[j]) < 0){
       return true;
     }else{
       return false;
     }
  }

  public static void main(String[] args) {
    PQ<Integer> intPQ = new PQ<Integer>(true);
    intPQ.add(12);
    intPQ.add(23);
    intPQ.add(7);
    intPQ.add(24);
    intPQ.add(92);
    intPQ.add(95);
    intPQ.add(19);
    intPQ.add(29);
    intPQ.add(49);
    intPQ.add(98);


    while (!intPQ.isEmpty()){
      System.out.println(intPQ.poll());
    }
  }
}
