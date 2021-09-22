package Graph.UG;

import java.util.Arrays;
import java.util.Stack;

public class DepthFirstPath {

  private Graph G;
  private boolean[] marked;
  private int[] edgeTo;
  private int start;

  public DepthFirstPath(Graph g, int s) {
    G = g;
    start = s;
    marked = new boolean[g.verticesCount()];
    edgeTo = new int[g.verticesCount()];
    dfs(s);
    //dfs_iter(s);
    System.out.println("edgeTo="+ Arrays.toString(edgeTo));
  }

  private void dfs(int s){
    marked[s] = true;
    System.out.print(s +" ->");
    for(int v: G.adj(s)){
      if (!marked[v]) {
        dfs(v);
        edgeTo[v] = s;
      }
    }
  }

  private void dfs_iter(int s){
    Stack<Integer> stack = new Stack<>();
    stack.push(s);
    while (!stack.isEmpty()){
      Integer visitedNode = stack.pop();
      marked[visitedNode] = true;
      System.out.print(visitedNode+"->");
      for(int v: G.adj(visitedNode)){
        if(!marked[v] && v!=visitedNode){
          stack.push(v);
        }
      }
    }
  }

  public boolean hasPathTo(int d){
    return marked[d];
  }

  public Iterable<Integer> pathTo(int d){
    if(!hasPathTo(d)){ return null; }
    Stack<Integer> path = new Stack<>();
    for(int i = d; i != start  ; i = edgeTo[i] ){
      path.push(i);
    }
    path.push(start);
    return path;
  }

}
