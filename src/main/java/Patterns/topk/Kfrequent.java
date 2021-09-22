package Patterns.topk;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class Kfrequent {

  public static void main(String[] args) {
    int[] arr = { 1, 3, 5, 12, 11, 12, 11,5,5 };
    getKFrequent(arr, 2);
  }

 static void getKFrequent(int[] arr, int k){
    Map<Integer, Integer> frequencyMap = new HashMap<>();
    //@todo precondition check
    for(int i = 0; i< arr.length; i++){
      frequencyMap.put(arr[i], frequencyMap.getOrDefault(arr[i], 0) + 1);
    }

   PriorityQueue<Entry<Integer, Integer>> frequencyQ = new PriorityQueue<>(
       (e1, e2) -> e1.getValue() - e2.getValue()
   );

    for(Entry<Integer, Integer> e: frequencyMap.entrySet()){
      int size = frequencyQ.size();
      if(size < k){
        frequencyQ.add(e);
      } else if( frequencyQ.peek().getValue() < e.getValue()){
        frequencyQ.poll();
        frequencyQ.add(e);
      }
    }

    System.out.println("done");
 }


}
