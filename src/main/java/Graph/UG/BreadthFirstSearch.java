package Graph.UG;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BreadthFirstSearch {

  private Graph G;
  private boolean[] visited;
  private int[] edgeTo;
  private int start;

  public BreadthFirstSearch(Graph g, int start) {
    visited = new boolean[g.verticesCount()];
    edgeTo = new int[g.verticesCount()];
    G = g;
    this.start = start;
    bfs(start);
  }

  private void bfs(int s){
    Queue<Integer> queue = new LinkedList<>();
    queue.add(s);
    while (!queue.isEmpty()){
      Integer visitedNode = queue.poll();
      visited[visitedNode] = true;
      for (Integer v: G.adj(visitedNode)){
        if (!visited[v]) {
          edgeTo[v] = visitedNode;
          queue.add(v);
        }
      }
    }
  }

  public boolean hasPathTo(int d){
    return visited[d];
  }

  public Iterable<Integer> pathTo(int d){
    if(!hasPathTo(d)){
      return null;
    }
    Stack<Integer> path = new Stack<>();
    for(int i = d; i != start  ; i = edgeTo[i] ){
      path.push(i);
    }
    path.push(start);
    return path;
  }

}
