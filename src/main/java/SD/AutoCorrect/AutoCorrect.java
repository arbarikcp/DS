package SD.AutoCorrect;

import com.google.common.collect.Sets;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AutoCorrect {

  static String alphabet = "abcdefghijklmnopqrstuvwxyz";
  String filePath;
  Set<String> knownStrings;

  public AutoCorrect(String filePath) throws IOException {
    this.filePath = filePath;
    initialize();
  }

  /*
   Create all the Strings after interchanging 2 adjacent chars
  */
  private static List<String> transposeAdjacentChars(Map<String, String> splitMap) {
    List<String> wordsTransposingAdjacentChar = new ArrayList<>();
    for (String firstPart : splitMap.keySet()) {
      String secondPart = splitMap.get(firstPart);
      if (secondPart.length() > 1) {
        String transposeString =
            new StringBuilder()
                .append(firstPart)
                .append(secondPart.charAt(1))
                .append(secondPart.charAt(0))
                .append(secondPart.substring(2))
                .toString();
        wordsTransposingAdjacentChar.add(transposeString);
      }
    }
    return wordsTransposingAdjacentChar;
  }

  /*
  Create all the Strings after replacing one chars from every place with a each alphabet char.
  */
  private static List<String> replaceOneChar(Map<String, String> splitMap) {
    List<String> wordAfterReplacingOneChar = new ArrayList<>();
    for (String firstPart : splitMap.keySet()) {
      String secondPart = splitMap.get(firstPart);
      for (char c : alphabet.toCharArray()) {
        String replaceString =
            new StringBuilder()
                .append(firstPart)
                .append(c)
                .append(secondPart.substring(1))
                .toString();
        wordAfterReplacingOneChar.add(replaceString);
      }
    }
    return wordAfterReplacingOneChar;
  }

  /*
  Create all the Strings after inserting one chars at every place with a each alphabet char.
  */
  private static List<String> insertOneChar(Map<String, String> splitMap) {
    List<String> wordAfterInsertingOneChar = new ArrayList<>();
    for (String firstPart : splitMap.keySet()) {
      String secondPart = splitMap.get(firstPart);
      for (char c : alphabet.toCharArray()) {
        String replaceString =
            new StringBuilder().append(firstPart).append(c).append(secondPart).toString();
        wordAfterInsertingOneChar.add(replaceString);
      }
    }
    return wordAfterInsertingOneChar;
  }

  public static void main(String[] args) throws IOException {

    String filePath = "/Users/abarik/pers/DS/src/main/java/SD/AutoCorrect/big.txt";
    AutoCorrect autoCorrect = new AutoCorrect(filePath);
    System.out.println(autoCorrect.nearByWords_1("childhly"));
    System.out.println(autoCorrect.nearByWords("childhly"));

    System.out.println(autoCorrect.splitTheWord("speling"));
    System.out.println(autoCorrect.deleteOneChar(autoCorrect.splitTheWord("spelling")));
    System.out.println(autoCorrect.transposeAdjacentChars(autoCorrect.splitTheWord("spelling")));
    System.out.println(autoCorrect.replaceOneChar(autoCorrect.splitTheWord("spelling")));
    System.out.println(autoCorrect.insertOneChar(autoCorrect.splitTheWord("spelling")));
  }

  private void initialize() throws IOException {
    knownStrings = readKnowWords();
  }

  private Set<String> readKnowWords() throws IOException {
    Set<String> knownWords = new HashSet<>();
    Path path = Paths.get(this.filePath);
    List<String> lines = Files.readAllLines(path);
    for (String str : lines) {
      String[] knownStrings = str.split("[\\W]");
      knownWords.addAll(new HashSet<>(Arrays.asList(knownStrings)));
    }
    return knownWords;
  }

  /*
   For example if the word is 'speling'  . It will split it as first_part and second_part from each position.
   {=speling, s=peling, sp=eling, spe=ling, spel=ing, speli=ng, spelin=g}

   When we do word transformations,
   by Deleting one char from every place or
   Transposing adjacent char or
   Inserting one alphabetic char at every place or
   Replacing one alphabetic char at every place

   This kind of Split map at every place will help us to reconstruct strings by applying above operations.

  */
  private Map<String, String> splitTheWord(String word) {
    HashMap<String, String> splitMap = new LinkedHashMap<>();
    splitMap.put("", word);
    for (int i = 1; i < word.length(); i++) {
      String firstPart = word.substring(0, i);
      String secondPart = word.substring(i, word.length());
      splitMap.put(firstPart, secondPart);
    }
    return splitMap;
  }

  /*
   Create all the Strings after deleting one char
  */
  private Set<String> deleteOneChar(Map<String, String> splitMap) {
    Set<String> wordsAfterDeletingOneChar = new LinkedHashSet<>();
    for (String firstPart : splitMap.keySet()) {
      String secondPart = splitMap.get(firstPart);
      if (secondPart.length() > 1) {
        secondPart = secondPart.substring(1);
      }
      wordsAfterDeletingOneChar.add(
          new StringBuilder().append(firstPart).append(secondPart).toString());
    }
    return wordsAfterDeletingOneChar;
  }

  /*
      Combine all the strings form above method, we will have all the Strings with edit distance 1
  */
  private Set<String> editsDistance1(String word) {
    Map<String, String> splitWord = splitTheWord(word);
    Set<String> editDistanceOneChars = new HashSet<>();
    editDistanceOneChars.addAll(deleteOneChar(splitWord));
    editDistanceOneChars.addAll(transposeAdjacentChars(splitWord));
    editDistanceOneChars.addAll(replaceOneChar(splitWord));
    editDistanceOneChars.addAll(insertOneChar(splitWord));
    return editDistanceOneChars;
  }

  /*
    Take all the strings from editDistance 1 and apply all the transformation again to get the
    edit distance 2 strings.
  */
  private Set<String> editsDistance2(String word) {
    Set<String> editDistance1Char = editsDistance1(word);
    Set<String> editDistance2Char = new HashSet<>();
    for (String str : editDistance1Char) {
      editDistance2Char.addAll(editsDistance1(str));
    }
    return editDistance2Char;
  }

  /*
    If the String is not a known string, Get the intersection of all the known words and all the generated Strings.
   */
  private Set<String> nearByWords_1(String word) throws IOException {
    Set<String> nearByWords = new HashSet<>();
    if (this.knownStrings.contains(word)) {
      nearByWords.add(word);
    } else {
      Set<String> editDistance1Words = editsDistance1(word);
      Set<String> edit1KnownWords = Sets.intersection(this.knownStrings, editDistance1Words);
      nearByWords.addAll(edit1KnownWords);
    }
    return nearByWords;
  }

  private Set<String> nearByWords(String word) throws IOException {
    Set<String> nearByWords = new HashSet<>();
    if (this.knownStrings.contains(word)) {
      nearByWords.add(word);
    } else {
      Set<String> editDistance1Words = editsDistance1(word);
      Set<String> editDistance2Words = editsDistance2(word);
      Set<String> edit1KnownWords = Sets.intersection(this.knownStrings, editDistance1Words);
      Set<String> edit2KnownWords = Sets.intersection(this.knownStrings, editDistance2Words);
      nearByWords.addAll(edit1KnownWords);
      nearByWords.addAll(edit2KnownWords);
    }
    return nearByWords;
  }
}
