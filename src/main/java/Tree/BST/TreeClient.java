package Tree.BST;

import Tree.StdIn;
import Tree.StdOut;
import java.util.Random;

public class TreeClient {

  public static void main(String[] args) {

    getDiameterTest();
    //checkPathSumTest();
    //PathSumTest();

    //printAllPaths();

    /*BinarySearchTree<String, Integer> st = new BinarySearchTree<>();
    for (int i = 0; !StdIn.isEmpty(); i++) {
      String key = StdIn.readString();
      st.put(key, i);
    }

    for (String s : st.keys())
      StdOut.println(s + " " + s);*/
  }

  public static  void PathSumTest(){
    BinarySearchTree<Integer, Integer> st = new BinarySearchTree<>();
    for (int i = 0; i < 20; i++) {
      Random random = new Random(31);
      Integer key = random.nextInt(200 + i);
      st.put(key, i);
      System.out.println("Added "+ key);
    }

    for (Integer s : st.keys())
      StdOut.println(s + " " + s);

    st.printPathSum();
  }

  public static  void checkPathSumTest(){
    BinarySearchTree<Integer, Integer> st = new BinarySearchTree<>();
    for (int i = 0; i < 20; i++) {
      Random random = new Random(31);
      Integer key = random.nextInt(200 + i);
      st.put(key, i);
      System.out.println("Added "+ key);
    }

    for (Integer s : st.keys())
      StdOut.println(s + " " + s);

    System.out.println(st.checkPathSum(816));

  }

  public static  void getDiameterTest(){
    BinarySearchTree<Integer, Integer> st = new BinarySearchTree<>();
      st.put(23, 23);
    st.put(29, 29);
    st.put(13, 13);
    st.put(19, 19);
    st.put(6, 6);
    st.put(4, 4);
    st.put(2, 2);

    for (Integer s : st.keys())
      StdOut.println(s + " " + s);

    System.out.println(st.diameter());

  }


  public static  void printAllPaths(){
    BinarySearchTree<Integer, Integer> st = new BinarySearchTree<>();
    for (int i = 0; i < 7; i++) {
      Random random = new Random(20);
      Integer key = random.nextInt(20 + i);
      st.put(key, i);
      System.out.println("Added "+ key);
    }

    for (Integer s : st.keys())
      StdOut.println(s + " " + s);

    st.printAllPaths();
  }
}
