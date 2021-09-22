package Patterns.SlidingWindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubStringWithNoRepeatingChar {

  public static void main(String[] args) {
    String s = "aabccbb";
     //getSubStringWithNoRepeatingChar_1("aabccbb");
     //getSubStringWithNoRepeatingChar_1("abbbb");
    //getSubStringWithNoRepeatingChar_1("abccde");
    getSubStringWithNoRepeatingChar_1("abcdbef");
  }

  public static void getSubStringWithNoRepeatingChar(String s) {
    int maxLengthSoFar = 0;
    int windowStart = 0;
    Map<Character, Integer> charsSoFar = new HashMap<>();
    for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
      Character c = s.charAt(windowEnd);
      if(charsSoFar.containsKey(c)){
        int lastIndexOfChar = charsSoFar.get(c);
        windowStart = Math.max(windowStart,lastIndexOfChar + 1);
      }
      charsSoFar.put(c, windowEnd);
      maxLengthSoFar = Math.max(maxLengthSoFar, windowEnd - windowStart + 1);
    }
    System.out.println("maxlength=" + maxLengthSoFar);
  }

  public static void getSubStringWithNoRepeatingChar_1(String s){
    int maxLengthSoFar = 0;
    int windowStart = 0, windowEnd = 0;
    Set<Character> charsSoFar = new HashSet<>();
    for(windowEnd = 0; windowEnd < s.length(); windowEnd ++){
      Character c =s.charAt(windowEnd);
      if(!charsSoFar.contains(c)){
        charsSoFar.add(c);
        maxLengthSoFar = Math.max(maxLengthSoFar, windowEnd - windowStart + 1);
      }else{
      windowStart = shrinkWindowFromStartChar(charsSoFar,windowStart, windowEnd,s,c);
      }
    }
    System.out.println("maxlength=" + maxLengthSoFar);
  }

  private static int shrinkWindowFromStartChar(
      Set<Character> charsSoFar, int windowStart, int windowEnd, String s, Character c) {
    while (windowStart < windowEnd && s.charAt(windowStart) != c) { // slide windowsStart until we find that char
      charsSoFar.remove(s.charAt(windowStart++));
    }
    if (s.charAt(windowStart) == c) { //As this char is already there, we need to move windowsStart to next of the char
      windowStart++;
    }
    return windowStart;
  }
}
