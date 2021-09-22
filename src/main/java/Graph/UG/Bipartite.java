package Graph.UG;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Bipartite {

  private Graph G;
  private color[] colors;;
  private boolean[] visited;
  private boolean isBipartite = true;
  public Bipartite(Graph g) {
    G = g;
    colors = new color[g.verticesCount()];
    visited = new boolean[g.verticesCount()];
    FindBipartite();
  }

  public boolean isBipartite() {
    if (isBipartite) {
      Set<Integer> color1 = new HashSet<>();
      Set<Integer> color2 = new HashSet<>();
      for (int v = 0; v < G.verticesCount(); v++) {
        if (colors[v] == color.COLOR1) {
          color1.add(v);
        } else {
          color2.add(v);
        }
      }
      System.out.println("color1 = " + color1);
      System.out.println("color2 = " + color2);
    }
    return isBipartite;
  }

  private void FindBipartite() {
    for(int i = 0; i < G.verticesCount(); i++){ //Run the dfs/bfs starting from each unvisited vertex
      if(!visited[i]){
        colors[i] = color.COLOR1; //This is start of a new connected component, color is as color1
        //dfs(i);
        bfs(i);
      }
    }
  }

  private void dfs(int s) {
    visited[s] = true;
    isBipartite = isValidBipartiteNode(s);
    //adjacent node will have different color than the source node
    color colorForAdjacentNodes = (colors[s] == color.COLOR1) ? color.COLOR2 : color.COLOR1;
    for (int v : G.adj(s)) {
      if (!visited[v]) {
        colors[v] = colorForAdjacentNodes;
        dfs(v);
      }
    }
  }

  private boolean isValidBipartiteNode(int s){
    for (int v : G.adj(s)) {
      //If adjacent nodes is colored with same as nodes color, then it is not bipartite
      if (colors[v] != null && colors[v] == colors[s]) {
        System.out.println("Graph is not bipartite");
        return  false;
      }
    }
    return true;
  }

  private void bfs(int start){
    Queue<Integer> queue = new LinkedList<>();
    colors[start] = color.COLOR1;// Color the start node as color1
    queue.add(start);
    while (!queue.isEmpty()){
      Integer visitedNode = queue.poll();
      visited[visitedNode] = true;
      if(isValidBipartiteNode(visitedNode)){
        color colorForAdjacentNodes = (colors[visitedNode] == color.COLOR1) ? color.COLOR2 : color.COLOR1;
        for(int v: G.adj(visitedNode)){
          if (!visited[v]) {
            colors[v] = colorForAdjacentNodes; // color the node and add to queue
            queue.add(v);
          }
        }
      }else{
        isBipartite = false;
        return ;
      }
    }
  }


enum color {
    COLOR1,
    COLOR2
  }

  public static void main(String[] args) {
    Graph g =  GraphGenerator.bipartite(5,7,9);
    Bipartite bp = new Bipartite(g);
    System.out.println(bp.isBipartite());
  }
}
