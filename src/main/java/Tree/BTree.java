package Tree;

import ElementarySort.SortUtil;

public class BTree<key extends Comparable<key>, value> {

  private static int order = 4;
  private Node root; //root of the BTree
  private int height; // height of BTree
  private int n; // number of key-value pair in the BTree

  private static class Entry{
    private Comparable key;
    private Object val;
    private Node next;

    public Entry(Comparable key, Object val, Node next) {
      this.key = key;
      this.val = val;
      this.next = next;
    }
  }

  private static class Node{
    private int m;// number of elements currently in the node
    private Entry[] children = new Entry[order];// Children in each node

    Node(int k){
      m = k;
    }
  }

  public BTree(){
    root = new Node(0); //Currently BTree has 0 nodes
  }

  public int size(){
    return n;
  }

  public boolean isEmpty(){
    return size() == 0;
  }

  public int height(){
    return height;
  }


  public value get(key key){
    return search(root,key, height);
  }

  private value search(Node x, key key, int ht){

    if(ht == 0){ //at leaf level, SO the real key-value will be stored here
      return searchKeyInLeafNode(x, key);
    } else{
        Node childNodeToSearch = getNextChildNodeToSearch(x, key);
        return search(childNodeToSearch,key, ht-1);
    }

  }

  //Traverse the keys in the node, and find the key j which is greater than this key, Then the corresponding value will be in the child of j - 1.
  private Node getNextChildNodeToSearch(Node x, key key){
    Entry[] children = x.children;
    int activeChildCountInNode = x.m;
    for(int i = 0; i < activeChildCountInNode; i++){
      if(i+1 == activeChildCountInNode || SortUtil.less(key, children[i+1].key)){
        return children[i].next;
      }
    }
    return null; // it should never reach here
  }

  //Search key in leaf-node/data-node. If our key is not here then it is not in the tree.
  private value searchKeyInLeafNode(Node x, key key){
    Entry[] children = x.children;
    int activeChildCount = x.m;
    for(int i = 0; i < activeChildCount ; i++){
      if(children[i].key.equals(key)){
        return (value)children[i].val;
      }else{
        return null;
      }
    }
    return null;
  }
}
