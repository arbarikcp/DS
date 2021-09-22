package TwoHeap;

import java.util.PriorityQueue;

public class MedianOfStream {

  PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> b-a);
  PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b) -> a-b);

  public static void main(String[] args) {
    MedianOfStream ms = new MedianOfStream();
    ms.insert(4);
    ms.insert(23);
    ms.insert(2);
    ms.insert(9);
    ms.insert(0);
    ms.insert(1);
    ms.insert(2);

    System.out.println(ms.getMedian());
  }

  public void insert(Integer num){

    // Check which heap the element will go
    if(maxHeap.size() == 0){
      maxHeap.add(num);
      return;
    }

    if(num > maxHeap.peek()){
      minHeap.add(num);
    }else{
      maxHeap.add(num);
    }

    reBalance();


  }

  public void reBalance(){
    if(minHeap.size() > maxHeap.size()){
      maxHeap.add(minHeap.poll());
    }else if(maxHeap.size() -1 > minHeap.size()){
      minHeap.add(maxHeap.poll());
    }
  }

  public Integer getMedian(){

    if(maxHeap.size()  == minHeap.size()){
      return (maxHeap.peek() + minHeap.peek()) /2;
    }else{
      return maxHeap.peek();
    }
  }

}
