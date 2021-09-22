package Patterns.SlidingWindow;

import java.util.HashMap;

public class LongestSubStringWithKDistinctChar {

  public static void main(String[] args) {
    getLongestSubStringWithKDistinctChar("araaci", 2); // 4, [0-3]
    getLongestSubStringWithKDistinctChar("araaci", 1); // 2, [2-3]
    getLongestSubStringWithKDistinctChar("cbbebi", 3); // 4, [1-4]
  }

  private static void getLongestSubStringWithKDistinctChar(String s, int k){

    int size = Integer.MIN_VALUE;
    int start = -1, end = -1;
    int windowStart = 0, windowEnd = 0;
    HashMap<Character, Integer> charFrequencyMap = new HashMap<>();
    for(windowEnd =0; windowEnd < s.length(); windowEnd++ ){
      Character c = s.charAt(windowEnd);
      charFrequencyMap.put(c, charFrequencyMap.getOrDefault(c,0) + 1);
      if(charFrequencyMap.size() > k){
        //slide windowStart until we have k number of characters left between windowStart and windowEnd
        while(windowStart < windowEnd && charFrequencyMap.size() > k){
          Character startChar= s.charAt(windowStart++); // make sure WindowStart moves, So for that first Store the data at windowStart location and move windowStart.
          // or else there will be a mistake of forgetting to move windowStart
          if(charFrequencyMap.containsKey(startChar)){
            charFrequencyMap.put(startChar,charFrequencyMap.get(startChar) - 1);
          }
          if(charFrequencyMap.get(startChar) <= 0){
            charFrequencyMap.remove(startChar);
          }
        }
      }
      if(charFrequencyMap.size() == k){
        int subArraySize = windowEnd - windowStart + 1;
        if(subArraySize > size){
          size = subArraySize;
          start = windowStart;
          end = windowEnd;
        }
      }
    }
    System.out.println("size="+ size + ", start="+ start+", end="+ end);
  }

}
