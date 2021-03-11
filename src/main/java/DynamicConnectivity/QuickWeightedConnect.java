package DynamicConnectivity;

import java.util.Arrays;

public class QuickWeightedConnect implements ConnectivityProvider {

  private final int size;
  int[] nodes;
  int[] connectedSetSize;

  public QuickWeightedConnect(int size) throws Exception {
    if (size <= 0) {
      throw new Exception("size must be positive");
    }
    this.size = size;
    this.nodes = new int[size];  // O(n) time and space complexity
    this.connectedSetSize = new int[size]; //O(n) time and space complexity
    int index = 0;
    while (index < size) {
      connectedSetSize[index] = 1;//O(n) time
      nodes[index] = index++;//O(n) time
    }
  }

  @Override
  //2log(n) + 2
  public void connect(int src, int dest) {
    if (isValid(size, src, dest) && !isConnected(src, dest)) {
      int srcRoot = getRoot(src); //log(n)
      int destRoot = getRoot(dest); //log(n)
      if (connectedSetSize[srcRoot] > connectedSetSize[destRoot]) {
        nodes[destRoot] = srcRoot;
        connectedSetSize[srcRoot] = connectedSetSize[srcRoot] + connectedSetSize[destRoot];
      } else {
        nodes[srcRoot] = destRoot;
        connectedSetSize[destRoot] = connectedSetSize[destRoot] + connectedSetSize[srcRoot];
      }
    }
    System.out.println(src + " -> " + dest + " ..." + Arrays.toString(nodes) + "... " + Arrays
        .toString(connectedSetSize));
  }

  @Override
  //2Log(n) time complexity
  public boolean isConnected(int src, int dest) {
    if(isValid(size,src,dest)) {
      return getRoot(src) == getRoot(dest);
    }
    return false;
  }

  //Log(n) time complexity
  private int getRoot(int index) {
    while (index != nodes[index]) {
      index = nodes[index];
    }
    return index;
  }
}
