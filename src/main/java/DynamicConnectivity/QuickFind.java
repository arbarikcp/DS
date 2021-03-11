package DynamicConnectivity;

import java.util.Arrays;

public class QuickFind implements ConnectivityProvider {

  private final int size;
  private int[] nodes;

  public QuickFind(int size) throws Exception {
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

  public void connect(int src, int dest) {
    if(isValid(size,src, dest) && !isConnected(src,dest)) {
      int srcParent = nodes[src];
      int destParent = nodes[dest];
      nodes[src] = nodes[dest];
      int index = 0;
      while (index < size) {
        if (nodes[index] == srcParent) {
          nodes[index] = destParent;
        }
        index++;
      }
    }
    System.out.println(src+" -> "+ dest +" ..."+ Arrays.toString(nodes));
  }

  public boolean isConnected(int src, int dest) {
    if(isValid(size,src,dest)) {
      return nodes[src] == nodes[dest];
    }
    return false;
  }

}
