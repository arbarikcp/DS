package Interview.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class BinarySearchTree {

  Node root;

  public static void main(String[] args) {
/*    BinarySearchTree bst = new BinarySearchTree();
    bst.create(Arrays.asList(34,21,13,40,25,61, 12, 45, 67, 98, 32, 78,24,23));
    bst.traverse();*/

    BinarySearchTree symmetricTree = new BinarySearchTree();
    symmetricTree.create(Arrays.asList(4,2,7,3,6,9,1,8));
/*    boolean isSymmetric = symmetricTree.isSymmetric(symmetricTree.root.left, symmetricTree.root.right );
    System.out.println(isSymmetric);
    inorder(symmetricTree.root);*/
/*    symmetricTree.convertToMirror(symmetricTree.root);
    System.out.println("After converting to mirror  ");
    inorder(symmetricTree.root);*/

   /* printAllPath(symmetricTree.root, new ArrayList<Integer>());*/
   /* printAncestors(symmetricTree.root, new ArrayList<Integer>(), 8);*/
    HashMap<Integer,List<Integer>> diagonals = new HashMap<>();
    // printRightDiagonal(symmetricTree.root, 0,diagonals);
    printLeftDiagonal(symmetricTree.root, 0, diagonals);
    System.out.println(diagonals);


  }

  public void create(List<Integer> keys){
    for(Integer key: keys){
      insert_iter(key); //use iterative method
      //root = insert(root, key);// use recursive method
    }
  }

  public void traverse(){
    //inorder(root);
    //preorder(root);
    //postorder(root);
    //levelOrder(root);
    Map<Integer, List<Integer>> levelKeys = new HashMap<>();
    levelOrder_rec(root, 0, levelKeys);
    System.out.println(levelKeys);

    Map<Integer, List<Integer>> levelKeys_z = new HashMap<>();
    levelOrder_zigzag(root, 0, levelKeys_z);
    System.out.println(levelKeys_z);

    Map<Integer, List<Integer>> levelDist = new HashMap<>();
    getHorizontalDistances(root, 0,levelDist);
    System.out.println(levelDist);

    System.out.println(height(root));
    System.out.println(rank(root,15));

    /*    topView();
    System.out.println("******************************");
    bottomView();*/
    /*    leftView();
    System.out.println("*******************************");
    rightView();*/

    /*     diameter(root, 0);
        System.out.println(min(root));
    System.out.println(max(root));

    System.out.println(floor(root,10));
    System.out.println(ceil(root,10));*/

    //printNodeAtLevel(root, 0);
    printCousins(root,2, root.left.left);
  }

  private static void inorder(Node current){
    if (current != null) {
      System.out.print(current.key + ",");
      inorder(current.left);
      inorder(current.right);
    }
  }

  private void preorder(Node current){
    if (current != null) {
      preorder(current.left);
      System.out.print(current.key + ",");
      preorder(current.right);
    }
  }

  private void postorder(Node current){
    if (current != null) {
      postorder(current.left);
      postorder(current.right);
      System.out.print(current.key + ",");
    }
  }


  private void levelOrder(Node node){
    if(node != null){
      Queue<Node> queue = new LinkedList();
      queue.offer(node);
      int level = 0;//Starting level is 0
      int levelNodeCount = queue.size() ; //node at current level is 1
      System.out.println("  Node count at level" + level +"= " + levelNodeCount);
      while (!queue.isEmpty()){
        Node current = queue.poll();
        System.out.print(current.key+",");
        if(current.left != null) queue.offer(current.left);
        if(current.right != null) queue.offer(current.right);
        levelNodeCount --; // Reduce the node from level count
        if(levelNodeCount == 0){ //It means all the nodes of current level are processed.
          levelNodeCount = queue.size(); //Node count of next level
          level++;
          System.out.println("  Node count at level" + level +"= " + levelNodeCount);
        }
      }
    }
  }

  private void levelOrder_rec(Node current, int level, Map<Integer, List<Integer>> keysInLevel){
    if(current != null){
      //Store the key in map as per level order, level is key in map
      List<Integer> keyList = keysInLevel.getOrDefault(level, new ArrayList<>());
      keyList.add(current.key );
      keysInLevel.put(level, keyList);

      levelOrder_rec(current.left, level + 1, keysInLevel);
      levelOrder_rec(current.right, level +1, keysInLevel);
    }
  }

  private void levelOrder_zigzag(Node current, int level, Map<Integer, List<Integer>> keysInLevel){
    if(current != null){
      //Store the key in map as per level order, level is key in map
      List<Integer> keyList = keysInLevel.getOrDefault(level, new ArrayList<>());
      //Based on the level add to the lists end or beginning
      if ((level %2 ) == 0) { //if level is even add it to normal order
        keyList.add(current.key);
      }else{
        keyList.add(0, current.key); //else add reverse order to list
      }
      keysInLevel.put(level, keyList);

      levelOrder_zigzag(current.left, level + 1, keysInLevel);
      levelOrder_zigzag(current.right, level +1, keysInLevel);
    }
  }

  private void printNodeAtLevel(Node node, int level){
    if(node == null || level < 0){
      return;
    }
    if(level == 0){
      System.out.print(node.key+",");
    }
    printNodeAtLevel(node.left , level-1);
    printNodeAtLevel(node.right, level-1);
  }

  private void printCousins(Node node, int level, Node src){
    if(node == null || level < 0){
      return;
    }
    if(level == 0 ){
      System.out.print(node.key+",");
    }

    if (!(node.left != null && node.left == src) || (node.right != null && node.right == src)) {
      printCousins(node.left, level - 1, src);
      printCousins(node.right, level - 1, src);
    }
  }

  private boolean isSymmetric(Node n1, Node n2){
    if(n1 == null && n2 == null) return  true; // base condition for symmetric
    if((n1 == null && n2 != null) || (n1 != null && n2 == null)) return  false;//condition for asymmetric
    return isSymmetric(n1.left, n2.right) && isSymmetric(n1.right, n2.left);
  }

  private void convertToMirror(Node node){
    if(node == null) return;

    Node temp =  node.left;
    node.left = node.right;
    node.right = temp;

    convertToMirror(node.left);
    convertToMirror(node.right);

  }

  private static boolean isLeafNode(Node node){
    return node.left == null && node.right ==null ;
  }

  private static void printAllPath(Node node, List<Integer> keys){
    if(node == null)return;

    keys.add(node.key); //Add the key to the Path

    if(isLeafNode(node)) //reached at leaf, Found one path from rot to leaf.
      System.out.println("path-"+keys);

    printAllPath(node.left, keys);
    printAllPath(node.right,keys);

    keys.remove((Object)node.key); //Remove the key from the path
  }

  private static void printAncestors(Node node, List<Integer> keys, Integer target){
    if(node == null){
      return;
    }

    if(node.key == target){ //reached at target
      System.out.println("path-"+keys);
    }
    keys.add(node.key);
    printAncestors(node.left, keys, target);
    printAncestors(node.right,keys, target);
    keys.remove((Object)node.key);
  }

  private static void printRightDiagonal(Node node,
      int diagonalCount,//diagonal count
      HashMap<Integer, List<Integer>> diagonals){//Map of diagonal counts to the keys in diagonal
    if(node == null) return;
    if(!diagonals.containsKey(diagonalCount)){
      diagonals.put(diagonalCount, new ArrayList<>());
    }
    diagonals.get(diagonalCount).add(node.key);

    printRightDiagonal(node.left, diagonalCount + 1, diagonals);
    printRightDiagonal(node.right, diagonalCount , diagonals);
  }

  private static void printLeftDiagonal(Node node,
      int diagonalCount, //diagonal count
      HashMap<Integer, List<Integer>> diagonals){ //Map of diagonal counts to the keys in diagonal
    if(node == null) return;
    if(!diagonals.containsKey(diagonalCount)){
      diagonals.put(diagonalCount, new ArrayList<>());
    }
    diagonals.get(diagonalCount).add(node.key);

    printLeftDiagonal(node.left, diagonalCount, diagonals);
    printLeftDiagonal(node.right, diagonalCount + 1 , diagonals);
  }

  private void getHorizontalDistances(Node current,int dist, Map<Integer, List<Integer>> keysInDist){
    if(current != null){
      List<Integer> keysList = keysInDist.getOrDefault(dist, new ArrayList<>());
      keysList.add(current.key);
      keysInDist.put(dist, keysList); //Store a Map of dist to list of nodes
      //roots dist is 0, left's dist is -1 of parents, right's dist is +1 of parent
      getHorizontalDistances(current.left, dist - 1, keysInDist);
      getHorizontalDistances(current.right,dist + 1, keysInDist);
    }
  }

  private void getHorizontalDistancesWithLevel(
      Node current,int dist,int level, Map<Integer, List<LeveToKeyPair>> distMap) {
    if (current != null) {
      if (!distMap.containsKey(dist)) {
        distMap.put(dist, new ArrayList<>());
      }
      distMap.get(dist).add(new LeveToKeyPair(level, current.key));
      getHorizontalDistancesWithLevel(current.left, dist-1, level+1, distMap);
      getHorizontalDistancesWithLevel(current.right, dist+1, level+1, distMap);
    }
  }

  private void getLevelsWithHorizontalDistances(
      Node current,int dist,int level, Map<Integer, List<DistToKeyPair>> levelMap) {
    if (current != null) {
      if (!levelMap.containsKey(level)) {
        levelMap.put(level, new ArrayList<>());
      }
      levelMap.get(level).add(new DistToKeyPair(dist, current.key));
      getLevelsWithHorizontalDistances(current.left, dist-1, level+1, levelMap);
      getLevelsWithHorizontalDistances(current.right, dist+1, level+1, levelMap);
    }
  }

  public void bottomView(){
    Map<Integer, List<LeveToKeyPair>> distToLevelKeyPairMap = new TreeMap<>();
    getHorizontalDistancesWithLevel(root, 0,0, distToLevelKeyPairMap);
    for(Integer dist: distToLevelKeyPairMap.keySet()){
      List<LeveToKeyPair> pairList = distToLevelKeyPairMap.get(dist);
      Collections.sort(pairList, LeveToKeyPair.compareByLevelDescending);
      System.out.println(pairList.get(0));
    }
  }
  public void topView(){
    Map<Integer, List<LeveToKeyPair>> distToLevelKeyPairMap = new TreeMap<>();
    getHorizontalDistancesWithLevel(root, 0,0, distToLevelKeyPairMap);
    for(Integer dist: distToLevelKeyPairMap.keySet()){
      List<LeveToKeyPair> pairList = distToLevelKeyPairMap.get(dist);
      Collections.sort(pairList, LeveToKeyPair.compareByLevel);
      System.out.println(pairList.get(0));
    }
  }

  public void rightView(){
    Map<Integer, List<DistToKeyPair>> levelToDistKeyPairMap = new TreeMap<>();
    getLevelsWithHorizontalDistances(root, 0,0, levelToDistKeyPairMap);
    for(Integer level: levelToDistKeyPairMap.keySet()){
      List<DistToKeyPair> pairList = levelToDistKeyPairMap.get(level);
      Collections.sort(pairList, DistToKeyPair.compareByDistDescending);
      System.out.println(pairList.get(0));
    }
  }

  public void leftView(){
    Map<Integer, List<DistToKeyPair>> levelToDistKeyPairMap = new TreeMap<>();
    getLevelsWithHorizontalDistances(root, 0,0, levelToDistKeyPairMap);
    for(Integer level: levelToDistKeyPairMap.keySet()){
      List<DistToKeyPair> pairList = levelToDistKeyPairMap.get(level);
      Collections.sort(pairList, DistToKeyPair.compareByDist);
      System.out.println(pairList.get(0));
    }
  }

  private int height(Node current){
    if(current != null){
      return 1 + Math.max(height(current.left), height(current.right));
    }else{
      return 0;
    }
  }

  private int diameter(Node current, int max){
    if(current != null){
      int diameterLengthByIncludingCurrentNode = 1 + diameter(current.left, max) + diameter(current.right,max);
      max = Math.max(diameterLengthByIncludingCurrentNode, max);
      return max;
    }else{
      return 0;
    }
  }

  private Integer min(Node current){
    if(current != null){
      while(current.left != null){ // Go to left most node
        current = current.left;
      }
      return current.key;
    }
    return null;
  }

  private Integer max(Node current){
    if(current != null){
      while(current.right != null){ // Go to Right most node
        current = current.right;
      }
      return current.key;
    }
    return null;
  }

  private Integer floor(Node current, Integer key) {
    if (current == null) return null;
    int cmp = key.compareTo(current.key);

    if (cmp == 0) return current.key; //Same key, so it is the floor

    if (cmp < 0) return floor(current.left, key); //key is less than current key, so floor will be in left subtree

    Integer t = floor(current.right, key); //floor may be in right subtree, or else current node is the floor.
    if (t == null) {
      return current.key;
    } else {
      return t;
    }
  }

  private Integer ceil(Node current, Integer key) {
    if (current == null) return null;
    int cmp = key.compareTo(current.key);

    if (cmp == 0) return current.key;//Same key, so it is the ceil

    if (cmp > 0) return ceil(current.right, key);//key is greater than current key, so ceil will be in right subtree

    Integer t = ceil(current.left, key);//Ceil may be in left subtree, or else current node is the floor.
    if (t == null) {
      return current.key;
    } else {
      return t;
    }
  }

  private Integer rank(Node current, Integer key){
    if(current == null) return 0;
    int cmp = key.compareTo(current.key);
    if(cmp > 0) return 1 + size(current.left) + rank(current.right, key);
    else if(cmp < 0) return rank(current.left,key);
    else return size(current.left);
  }

  private Node insert(Node parent, Integer key){
    if(parent == null){
      return new Node(key);
    }
    int cmp = key.compareTo(parent.key);
    if(cmp < 0) { parent.left = insert(parent.left, key);}
    else if(cmp > 0){parent.right = insert(parent.right, key);}
    parent.size = 1 + size(parent.left) + size(parent.right);
    return parent;
  }

  private Integer size(Node t){
    if(t == null) return 0;
    else return t.size;
  }

  private boolean insert_iter(Integer key){
    Node current = root;
    Node parent = root;

    while(current != null){
      parent = current;
      int cmp = key.compareTo(current.key);
      if(cmp < 0){current.size = current.size +1;current = current.left;}
      else if(cmp > 0){current.size = current.size +1;current = current.right;}
      else{current.key = key; return true;}
    }

    Node newNode = new Node(key);
    if(parent == null){
      root = newNode;
    }else{
      int cmp = key.compareTo(parent.key);
      if(cmp < 0){
        parent.left = newNode;
      }else{
        parent.right = newNode;
      }
    }
    return true;
  }

  static class Node{
    Node left,mid, right;
    int key;
    int size;

    public Node(int key) {
      this.key = key;
      this.left = null;
      this.mid = null;
      this.right = null;
      this.size = 1;
    }
    public Node(int key, Node left, Node right) {
      this.key = key;
      this.left = left;
      this.right = right;
      this.size = 1;
    }

    public Node(int key, Node left,Node mid, Node right) {
      this.key = key;
      this.left = left;
      this.mid = mid;
      this.right = right;
      this.size = 1;
    }

    public String toString(){
      return ""+key+"";
    }
  }

  static class DistToKeyPair{
    static Comparator<DistToKeyPair> compareByDist = (p1, p2) -> p1.dist - p2.dist;
    static Comparator<DistToKeyPair> compareByDistDescending = (p1, p2) -> p2.dist - p1.dist;

    int dist;
    int key;

    public DistToKeyPair(int dist, int key) {
      this.dist = dist;
      this.key = key;
    }

    public String toString(){
      return "("+dist+","+ key+")";
    }
  }

  static class LeveToKeyPair{
    static Comparator<LeveToKeyPair> compareByLevel = (p1, p2) -> p1.level - p2.level;
    static Comparator<LeveToKeyPair> compareByLevelDescending = (p1, p2) -> p2.level - p1.level;

    int level;
    int key;

    public LeveToKeyPair(int level, int key) {
      this.level = level;
      this.key = key;
    }

    public String toString(){
      return "("+level+","+ key+")";
    }
  }

  static class MNode{
    Integer key;
    List<MNode> child;

    public MNode(Integer key) {
      this.key = key;
      child = new ArrayList<>();
    }
  }

}
