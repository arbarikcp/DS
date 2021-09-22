package Graph.UG;

import java.util.ArrayList;

public class Graph {

  private final int V; //Total vertex count
  private int E; //total edge count
  private final ArrayList[] adj ; //Each index represents each vertex

  public Graph(int v) {
    V = v;
    E = 0;
    this.adj = new ArrayList[v];
    for (int i = 0; i < v ; i++){
      adj[i] = new ArrayList<>();
    }
  }

  public int E(){
    return E;
  }
  public int V(){
    return verticesCount();
  }
  public int verticesCount(){ return V;}

  public void addEdge(int u, int v){
    adj[u].add(v);
    adj[v].add(u);
    E =  E + 1;
  }

  public Iterable<Integer> adj(int u){ return adj[u]; }

  public int adjNodeCount(int u) { return adj[u].size();}

  public int degree(int u){ return adj[u].size();}

  public int maxDegree(){
    int max = 0;
    for(int i = 0; i < V; i++){
      if(adj[i].size() > max)   max = adj[i].size();
    }
    return max;
  }

  public int numberOfSelfLoop(){
    int count = 0;
    for(int v = 0; v < V; v++){
      for(int u : adj(v)){
        if(u == v ) count ++;
      }
    }
    return count / 2;
  }

}
