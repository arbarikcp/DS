package Tree.BST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinarySearchTree<key extends Comparable<key>, value> {

  private Node root;
  private int size;

  private class Node{
    private key key;
    private value value;// Value is need for key-value kind of tree structure
    private Node left, right;
    private int size;// Keeps the count of nodes at this subtree root

    public Node(key key, value value) {
      this.key = key;
      this.value = value;
      this.left = null;
      this.right = null;
      this.size = 1;
    }
  }

  public BinarySearchTree() { }

  public int size(){
    return  size;
  } //returns size of tree

  public boolean isEmpty(){
    return size == 0;
  }

  private int size(Node x){ //returns size of subtree rooted at x
    return x == null ? 0 :  x.size;
  }

  public void put(key key, value value){
    root = insert(root, key, value);
  }

  public void put_(key key, value value){
    insert_iter(key, value);
  }

  public Iterable<key> keys(){
    List<key> keysList = new ArrayList();
    keys(root,keysList);
    return keysList;
  }

  private void keys(Node x, List<key> keys){
    if( x == null) return;
    keys(x.left, keys);
    keys.add(x.key);
    keys(x.right, keys);
  }


  private Node insert( Node parent, key key, value value ){
    if(parent == null) return new Node(key, value);
    int cmp = key.compareTo(parent.key);
    if(cmp < 0)parent.left = insert(parent.left, key, value);
    else if(cmp > 0) parent.right = insert(parent.right, key, value);
    else parent.value = value; // This is only needed a key-value API.
    parent.size = 1 + size(parent.left) + size(parent.right); // update the size
    return parent;
  }

  private boolean insert_iter(key key, value value){
    Node  current = root;
    Node parent = root;
    while (current != null){
      parent = current; //Keep the parent to the current node.
      int cmp = key.compareTo(current.key);
      if(cmp < 0){ current = current.left; parent.size = parent.size + 1;}
      else if(cmp > 0) {current = current.right; parent.size = parent.size + 1;}
      else current.value = value;
    }
    // We will come out of loop, when the current becomes null.
    //So it means the node must be child of parent ( parent of current)
    //We need to check if parent itself is null, or else our key will be left or right of parent.
    Node newElement = new Node(key, value);
    if(parent == null){
      root = newElement;}
    else{
      int cmp = key.compareTo(parent.key);
      if(cmp < 0) parent.left = newElement;
      else if(cmp > 0) parent.right = newElement;
    }
    return true;
  }

  public void printPathSum(){
    pathSum(root, new ArrayList<key>());
  }

  public void printAllPaths(){
    printPaths(root, new ArrayList<key>());
  }

  private void printPaths(Node curr, List<key> keys){
    keys.add(curr.key);
    if(curr.left == null && curr.right == null ){
      for(key k: keys){
        System.out.print(k+"->");
      }
      System.out.println();
    }
    if (curr.left != null) {
      printPaths(curr.left, keys);
      }
    if (curr.right != null) {
      printPaths(curr.right, keys);
      }
    keys.remove(curr.key);
  }

  private void pathSum(Node curr, List<key> keys){
    keys.add(curr.key);
    if(curr.left == null && curr.right == null){
      int pathSum = getSum(keys);
      for(key k: keys){
        System.out.print(k+"->");
      }
      System.out.println("("+pathSum+")");
    }
    if(curr.left != null){
      pathSum(curr.left, keys);
    }
    if(curr.right != null){
      pathSum(curr.right, keys);
    }
    keys.remove(curr.key);

  }

  public boolean checkPathSum(int sum){
    return checkPathSum(root, sum);
  }

  private boolean checkPathSum(Node curr, Integer sum){
    if(curr == null) return false;
    if(curr.left == null && curr.right == null){
      System.out.println("required sum="+ sum +", leaf key="+ curr.key );
      if (((Integer) curr.key).equals(sum)) {
        return true;
      }else {
        return false;
      }
    }
    return
      checkPathSum(curr.left,sum - (Integer) curr.key) ||
      checkPathSum(curr.right,sum - (Integer) curr.key);
  }

  public int diameter(){
    return diameter(root, 0);
  }

  private int diameter(Node current, int maxLength){
      if(current == null){
        return 0;
      }
      int length = 1 + diameter(current.left,maxLength ) + diameter(current.right, maxLength);
      maxLength = Math.max(maxLength, length);
      return maxLength;
  }

  private int getSum(List<key> keys){
    int sum = 0;
    for(key k: keys){
      sum = sum + (Integer) k;
    }
    return  sum;
  }

}
