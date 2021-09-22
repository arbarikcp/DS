package DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringConstruct {

  public static void main(String[] args) {


    System.out.println(
        new StringConstruct()
            .allConstruct("abcdef", new String[] {"ab", "abc","cd", "def","ef","c", "cd", "abcd"}));

    System.out.println(
        new StringConstruct()
            .allConstruct("aaaaaaaaaaaaaaaaz", new String[] {"a", "aa","aaa", "aaaa","aaaaa","aaaaaa"}));


/*    System.out.println(
        new StringConstruct()
            .canConstruct("abcdef", new String[] {"ab", "abc", "def", "cd", "abcd"}));
    System.out.println(
        new StringConstruct()
            .howConstruct("abcdef", new String[] {"ab", "abc", "defg", "cd", "abcd", "ef"}));

    System.out.println(
        new StringConstruct()
            .bestConstruct("abcdef", new String[] {"ab", "abc", "defg", "cd", "abcd", "ef"}));*/
  }

  public boolean canConstruct(String target, String[] words) {
    if (target.equals("")) {
      return true;
    }

    for (int i = 0; i < words.length; i++) {
      if (target.startsWith(words[i])) {
        String reminder = target.substring(words[i].length());
        if (canConstruct(reminder, words)) {
          return true;
        }
      }
    }
    return false;
  }

  public List<String> howConstruct(String target, String[] words) {

    Map<String, List<String>> memo = new HashMap<>();
    return howConstruct(target, words, memo);
  }

  public List<String> howConstruct(String target, String[] words, Map<String, List<String>> memo) {
    if (memo.containsKey(target)) {
      return memo.get(target);
    }

    if (target.equals("")) {
      return new ArrayList<>();
    }

    for (int i = 0; i < words.length; i++) {
      if (target.startsWith(words[i])) {
        String reminder = target.substring(words[i].length());
        List<String> result = howConstruct(reminder, words, memo);
        if (result != null) {
          result.add(words[i]);
          memo.put(target, result);
          return result;
        }
      }
    }
    memo.put(target, null);
    return null;
  }

  public List<String> bestConstruct(String target, String[] words) {

    Map<String, List<String>> memo = new HashMap<>();
    return bestConstruct(target, words, memo);
  }

  public List<String> bestConstruct(String target, String[] words, Map<String, List<String>> memo) {
    if (memo.containsKey(target)) {
      return memo.get(target);
    }

    if (target.equals("")) {
      return new ArrayList<>();
    }

    List<String> bestResult = null;
    for (int i = 0; i < words.length; i++) {
      if (target.startsWith(words[i])) {
        String reminder = target.substring(words[i].length());
        List<String> result = bestConstruct(reminder, words, memo);
        if (result != null) {
          result.add(words[i]);
          if (bestResult == null || bestResult.size() > result.size()) {
            bestResult = result;
          }
        }
      }
    }
    memo.put(target, copyList(bestResult));
    return bestResult;
  }



  public List<List<String>> allConstruct(String target, String[] words) {

    Map<String, List<List<String>>> memo = new HashMap<>();
    return allConstruct(target, words, memo);

  }

  public List<List<String>> allConstruct(String target, String[] words,Map<String, List<List<String>>> memo) {
    if(memo.containsKey(target)){
      return memo.get(target);
    }

    if (target.equals("")) {
      List<List<String>> baseResult =new ArrayList<>();
      baseResult.add(new ArrayList<>());
      return baseResult; //
    }

    List<List<String>> allSolutions = new ArrayList<>();
    for(int i = 0; i < words.length; i++){
      if(target.startsWith(words[i])){
        String subProblem = target.substring(words[i].length());
        List<List<String>> subSolution = allConstruct(subProblem, words, memo);
        if(subSolution != null){
          for(List<String> soln: subSolution ){
            soln.add(words[i]);
          }
          for(List<String> soln: subSolution){
            allSolutions.add(copyList(soln));
          }
        }
      }
    }

    memo.put(target, copyList_1(allSolutions));
    return allSolutions;

  }



  private List<String> copyList(List<String> src) {
    if (src != null) {
      List<String> dest = new ArrayList<>(src);
      Collections.copy(dest, src);
      return dest;
    } else {
      return null;
    }
  }

  private List<List<String>> copyList_1(List<List<String>> src) {
    if (src != null) {
      List<List<String>> dest = new ArrayList<>();
      for(List<String> stringList: src){
        dest.add(copyList(stringList));
      }
      return dest;
    } else {
      return null;
    }
  }
}
