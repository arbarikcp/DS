package DynamicConnectivity;

import java.util.Arrays;

public class QuickFind implements ConnectivityProvider {

  private final int size;
  private int[] nodes;

  //For each connect op, it takes O(n) time. For M Connect Op , it will be O(Mn)
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
      nodes[src] = nodes[dest]; // Change the src's parent to dest's  parent
      int index = 0;
      //O(n) time complexity
      while (index < size) {  // Change all those nodes whose parent was same as src's parent to dest's parent.
        if (nodes[index] == srcParent) {
          nodes[index] = destParent;
        }
        index++;
      }
    }
    System.out.println(src+" -> "+ dest +" ..."+ Arrays.toString(nodes));
  }

  //O(1) time
  public boolean isConnected(int src, int dest) {
    if(isValid(size,src,dest)) {
      return nodes[src] == nodes[dest];
    }
    return false;
  }

}
