package DynamicConnectivity;

import java.util.Arrays;

public class QuickConnect implements ConnectivityProvider {

  private final int size;
  int[] nodes;

  public QuickConnect(int size) throws Exception {
    if (size <= 0) {
      throw new Exception("size must be positive");
    }
    this.size = size;
    this.nodes = new int[size];
    int index = 0;
    while (index < size) {
      nodes[index] = index++;
    }
  }

  //2(logn) average case time and  2 * O(n) in case of skinny tree (sequential client)
  public void connect(int src, int dest) {

    if(isValid(size, src,dest) && !isConnected(src, dest)){
      int srcRoot = getRoot(src);
      int destRoot = getRoot(dest);
      nodes[srcRoot] = destRoot;
    }
    System.out.println(src+" -> "+ dest +" ..."+ Arrays.toString(nodes));
  }
  public boolean isConnected(int src, int dest) {
    if(isValid(size,src,dest)){
      return getRoot(src) == getRoot(dest);
    }
    return false;
  }

  //O(logn) time average case, if the tree become sequential, then it is O(n)
  private int  getRoot(int src){
    int parent = src;
    while(nodes[parent] != parent){
      parent = nodes[parent];
    }
    return parent;
  }

}
