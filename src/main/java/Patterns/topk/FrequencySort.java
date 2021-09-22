package Patterns.topk;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class FrequencySort {

  public static void main(String[] args) {
    sort("Programming");
  }

  public static void sort(String src) {
    // @todo precondition
    Map<Character, Integer> frequencyMap = new HashMap<>();
    // create frequency Map
    for (int i = 0; i < src.length(); i++) {
      Character character = src.charAt(i);
      frequencyMap.put(character, frequencyMap.getOrDefault(character, 0) + 1);
    }

    //Create a MaxHeap
    Comparator<Entry<Character, Integer>> maxHeapCompartaor =
        (e1, e2) -> e2.getValue() - e1.getValue();
    PriorityQueue<Entry<Character, Integer>> queue = new PriorityQueue<>(maxHeapCompartaor);

    //Add all element to maxHeap based on char frequency
    for (Entry<Character, Integer> e : frequencyMap.entrySet()) {
      queue.add(e);
    }

    //get the characters from the priorityQ
    StringBuilder sb = new StringBuilder();
    while (!queue.isEmpty()) {
      Character c = queue.poll().getKey();
      int count = frequencyMap.get(c);
      for (int i = 0; i < count; i++) {
        sb.append(c);
        // System.out.print(c);
      }
    }
    System.out.println(sb.toString());
  }
}
