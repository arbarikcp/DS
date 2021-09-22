package Graph.DG;

import java.util.HashSet;
import java.util.Set;
import utils.In;

public class Reachability {

  private Digraph G;
  int start;
  Set<Integer> reachableFromS = new HashSet<>();
  Set<Integer> sIsReachableFrom = new HashSet<>();

  boolean[] visited;
  boolean[] visited_r;

  public Reachability(Digraph g, int start) {
    G = g;
    this.start = start;
    visited = new boolean[g.V()];
    visited_r = new boolean[g.V()];
    dfs(g,start,reachableFromS, visited);
    dfs(g.reverse(), start, sIsReachableFrom,visited_r);
  }

  private  void dfs(Digraph g, int v, Set reachable, boolean[] marked){
    marked[v] = true;
    reachable.add(v);
    for(int adj: g.adj(v)){
      if(!marked[adj]){
        dfs(g, adj,reachable,marked);
      }
    }
  }

  public Set<Integer> getReachableFromS(){
    return reachableFromS;
  }

  public Set<Integer> getSIsReachableFrom(){
    return sIsReachableFrom;
  }

  public static void main(String[] args) {
    In in = new In("/Users/abarik/pers/DS/src/main/java/Graph/DG/resource/tinyDG.txt");
    Digraph G = new Digraph(in);

    Reachability rc = new Reachability(G, 0);
    System.out.println(rc.getReachableFromS());
    System.out.println(rc.getSIsReachableFrom());
  }
}
