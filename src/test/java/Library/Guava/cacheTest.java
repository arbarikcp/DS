package Library.Guava;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class cacheTest {

  public static void main(String[] args) {

    Node n1 = new Node("12", "abc");
    Node n2 = new Node("12", "abc");

    Map<Node, Integer> nodeMap =  new TreeMap<>(new KeyBasedComparator());
    nodeMap.put(n1,12);
    nodeMap.put(n2, 23);

    System.out.println(nodeMap.size());
  }

  static class Node{
    private String key;

    private String value;

    public Node(String key, String value) {
      this.key = key;
      this.value = value;
    }
  }

  static class KeyBasedComparator implements Comparator<Node>{

    @Override
    public int compare(Node o1, Node o2) {
      return  o1.key.compareTo(o2.key);
    }
  }

}
