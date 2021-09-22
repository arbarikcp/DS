package Graph.UG;


import Tree.StdOut;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstPaths {

  private static  Integer INFINITY = Integer.MAX_VALUE;
  private  Graph g;
  private boolean[] visited;
  private int[] edgeTo;
  private int[] distTo;
  private int start;

  public BreadthFirstPaths(Graph g, int start) {
    this.g = g;
    int count = g.verticesCount();
    visited = new boolean[count];
    edgeTo = new int[count];
    distTo = new int[count];
    for(int i = 0; i < count; i++){
      distTo[i] = INFINITY; // We need this to indicate some node is not reachable from start node s.
    }
    start = start;
    bfs(start);
  }


  public void bfs(int start){
    Queue<Integer> queue = new LinkedList<>();
    distTo[start] = 0;
    queue.add(start);
    while (!queue.isEmpty()){
      int currentNode = queue.poll();
      visited[currentNode] = true;
      for(int v: g.adj(currentNode)){
        if(!visited[v]){
          distTo[v] = distTo[currentNode] + 1;
          edgeTo[v] = currentNode;
          queue.add(v);
        }
      }
    }
  }

  public boolean hasPathTo(int dest){
    return visited[dest];
  }

  public int distTo(int dest){
    return distTo[dest];
  }

  public List<Integer> pathTo(int dest){
    if(hasPathTo(dest)){
      List<Integer> path = new ArrayList<>();
      path.add(dest);
      while(edgeTo[dest] != dest){
        path.add(edgeTo[dest]);
        dest = edgeTo[dest];
      }
      Collections.reverse(path);
      return path;
    }else{
      System.out.println("no path from "+ start + " to "+ dest);
      return null;
    }
  }

  public static void main(String[] args) {
    int verticesCount = 21;
    int edgeCount = 59;
    Graph G = GraphGenerator.simple(verticesCount, edgeCount);
    StdOut.println(G);

    BreadthFirstPaths bfsPath = new BreadthFirstPaths(G, 0);
    System.out.println(bfsPath.pathTo(12));

  }

}
