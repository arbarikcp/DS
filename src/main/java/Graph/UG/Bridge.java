package Graph.UG;

import Tree.StdOut;
import java.util.HashSet;
import java.util.Set;

public class Bridge {
  private int bridges;                    // number of bridges
  private Set<Integer> articulationPoints; //all articulation points
  private int order;                      // counter
  private int[] traverseOrder;           // pre[v] = order in which dfs examines v
  private int[] lowestBackEdgeTo;        // low[v] = lowest preorder of any vertex connected to v

  public Bridge(Graph G) {
    articulationPoints = new HashSet<>();
    lowestBackEdgeTo = new int[G.V()];
    traverseOrder = new int[G.V()];
    for (int v = 0; v < G.V(); v++)
      lowestBackEdgeTo[v] = -1;
    for (int v = 0; v < G.V(); v++)
      traverseOrder[v] = -1;

    for (int v = 0; v < G.V(); v++)
      if (traverseOrder[v] == -1)
        dfs(G, v, v);

  }

  public int components() { return bridges + 1; }

  public Set<Integer> getArticulationPoints(){ return articulationPoints;}

  private void dfs(Graph G, int parent, int currentNode) {
    traverseOrder[currentNode] = order++;
    lowestBackEdgeTo[currentNode] = traverseOrder[currentNode];
    for (int adjNode : G.adj(currentNode)) {
      if (traverseOrder[adjNode] == -1) { // Adjacent node is not visited
        dfs(G, currentNode, adjNode);
        lowestBackEdgeTo[currentNode] = Math.min(lowestBackEdgeTo[currentNode], lowestBackEdgeTo[adjNode]);
        if (lowestBackEdgeTo[adjNode] == traverseOrder[adjNode]) {
          StdOut.println(currentNode + "-" + adjNode + " is a bridge");
          bridges++;
        }
        //adjacent node has no backedge which can be reached before traversing current node
        if(traverseOrder[currentNode] <= lowestBackEdgeTo[adjNode]){
          StdOut.println( currentNode +" is an articulation point");
          articulationPoints.add(currentNode);
        }
      }
      // Adjacent node is already visited, So it means it has a back edge through this node.
      //If there is backedge through this adjacent node which is lower than the existing back edge
      // of current node, then take the adjacent's traverse order as currents lowest back edge.
      //update lowestBackedge by anyone of below statements, it should be good.
      else if (adjNode != parent)// update low number - ignore reverse of edge leading to v,
        //lowestBackEdgeTo[currentNode] = Math.min(lowestBackEdgeTo[currentNode], traverseOrder[adjNode]);
        lowestBackEdgeTo[currentNode] = Math.min(lowestBackEdgeTo[currentNode], lowestBackEdgeTo[adjNode]);
      }
    //Root or the start node of DFS ia an articulation point if it has more than one edge.
    if(currentNode == parent && G.adjNodeCount(currentNode) > 1){
      StdOut.println( currentNode +" is an articulation point");
      articulationPoints.add(currentNode);
    }
  }

  // test client
  public static void main(String[] args) {
    int verticesCount = 6;
    int edgeCount = 7;
    Graph G = GraphGenerator.simple(verticesCount, edgeCount);
    StdOut.println(G);

    Bridge bridge = new Bridge(G);
    StdOut.println("Edge connected components = " + bridge.components());
    StdOut.println("Articulation points are  = " + bridge.getArticulationPoints());
  }


}