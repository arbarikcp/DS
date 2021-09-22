package Trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Trie {
  TrieNode head;

  public Trie() {
    this.head = new TrieNode();
  }

  public void put(String key){
    TrieNode curr = head;
    for(Character c : key.toCharArray()){ //Go through each character
      curr.children.putIfAbsent(c, new TrieNode());
      curr.count += 1; // One more string has been inserted in this path, so count increased
      curr = curr.children.get(c); //Move down the path
    }
    curr.isLeaf = true; // leaf is only true if any String ends here.
    curr.key = key;
  }

  public boolean search(String key){
    TrieNode curr = head;
    for(Character c: key.toCharArray()){
      curr = curr.children.get(c); //Go to the child having same char
      if(curr == null) {
        return false;
      }
    }
    return curr.isLeaf; // Once all chars completed, check if we are at leaf.
  }

  private TrieNode findPrefixNode(String prefixKey){
    TrieNode curr = head;
    for(Character c : prefixKey.toCharArray()){
      if(curr.children.get(c) == null){
        return null;
      }else{
        curr = curr.children.get(c);
      }
    }
    return  curr;
  }

  public String findShortestUniquePrefix(){
    TrieNode curr = head;
    StringBuilder builder = new StringBuilder();
    //if any node has more than one child, then its not unique prefix
    while (curr.children.size() == 1){
      for(Character key: curr.children.keySet()){
        builder.append(key); // take that character, it is part of prefix
        curr = curr.children.get(key);//traverse the path
      }
    }
    return  builder.toString();
  }

  public List<String> prefixSearch(String prefix){
    List<String> keys = new ArrayList<>();
    TrieNode prefixNode = findPrefixNode(prefix);
    if(prefixNode != null){
      traverse(prefixNode,new StringBuilder(), keys);
    }
    List<String> matchedKeys = new ArrayList<>();
    for(String k: keys){
      matchedKeys.add(prefix+k);
    }
    return matchedKeys;
  }

  private void traverse(TrieNode curr, List<String> keys){
    if(curr == null){ return; }
    if(curr.isLeaf){//If keys are in leaf node, add to the keys list
      keys.add(curr.key);
    }
    for(Character c: curr.children.keySet()){
      traverse(curr.children.get(c), keys);
    }
  }

  //if we don't want to store the entier key in leaf,
  //then we need to construct it during traversal.
  private void traverse(TrieNode curr, StringBuilder builder,List<String> keys){
    if(curr == null){ return; }
    //Only construct when we reached at leaf.
    if(curr.isLeaf){ keys.add(builder.toString());}
    for(Character c: curr.children.keySet()){
      builder.append(c);
      traverse(curr.children.get(c),builder,keys);
    }
    if (builder.length() >= 1) { //while we are tracking back remove the char
      builder.deleteCharAt(builder.length() - 1);
    }
  }

  public List<String> traverse(){
    List<String> keys = new ArrayList<>();
    traverse(head, new StringBuilder(),keys);
    return keys;
  }

  class TrieNode{

    Map<Character, TrieNode> children; // children of the node
    boolean isLeaf; // if it is a leaf node
    int count; // how many strings has inserted in this path
    String key; //We can store the entier String at the leaf node

    public TrieNode(){
      this.children = new TreeMap<Character, TrieNode>();
    }
  }

  public static void main(String[] args) {
    // construct a new Trie node
    Trie head = new Trie();
/*    String s = "lexicographic sorting of a set of keys can be accomplished with " +
        "a simple trie based algorithm we insert all keys in a trie output " +
        "all keys in the trie by means of preorder traversal which results " +
        "in output that is in lexicographically increasing order preorder " +
        "traversal is a kind of depth first traversal";*/

    String s = "hello world are you ";
    // insert all keys of a dictionary into a Trie
    for (String word: s.split(" ")) {
      head.put(word);
    }

    // head.preorder(head);
    System.out.println(head.findShortestUniquePrefix());
    //System.out.println(head.traverse());
    //System.out.println(head.prefixSearch("he"));
  }
}

