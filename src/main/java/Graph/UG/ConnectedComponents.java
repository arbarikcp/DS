package Graph.UG;

import java.util.LinkedList;
import java.util.Queue;

public class ConnectedComponents {

  private Graph G;
  private int[] ccId;
  private boolean[] visited;
  private int componentId = 0;

  public ConnectedComponents(Graph g) {
    G = g;
    visited = new boolean[G.verticesCount()];
    ccId = new int[G.verticesCount()];
    findCC();
  }

  public boolean isConnected(int u, int v) {
    return ccId[u] == ccId[v];
  }


//componentId initialize to 0
  private void findCC() {
    for (int v = 0; v < G.verticesCount(); v++) {
      if (!visited[v]) {
        dfs(v); // dfs or bfs. any traversal, as soon as we reach the node. Mark its ccId as the current ccId
        //bfs(v);
        componentId++;
      }
    }
  }

  private void dfs(int s) {
    ccId[s] = componentId;
    visited[s] = true;
    for (int v : G.adj(s)) {
      if (!visited[v]) {
        dfs(v);
      }
    }
  }

  public int count() {
    return componentId;
  }
  public int getCCId(int u) {
    return ccId[u];
  }

  private void bfs(int s){
    Queue<Integer> queue = new LinkedList<>();
    queue.add(s);
    while (!queue.isEmpty()){
      int visitedNode = queue.poll();
      visited[visitedNode] = true;
      ccId[visitedNode] = componentId;
      for(int v : G.adj(visitedNode)){
        if(!visited[v]){
          queue.add(v);
        }
      }
    }
  }

}
