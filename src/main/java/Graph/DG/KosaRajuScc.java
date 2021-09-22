package Graph.DG;

import Tree.StdOut;
import java.util.Stack;
import org.apache.yetus.audience.InterfaceAudience.Private;
import utils.In;

public class KosaRajuScc {

  private Digraph G;
  private int[] sccIds;
  private boolean[] visited;
  int sccId = 0;

  public KosaRajuScc(Digraph g) {
    G = g;
    visited = new boolean[g.V()];
    sccIds = new int[g.V()];
    runKosaRajuAlgo();
  }

  public int countOfSCC(){
    return sccId;
  }
  public boolean isStronglyConnected(int u, int v){
    return sccIds[u] == sccIds[v];
  }

  private void runKosaRajuAlgo(){
    ReversePostOrder RPO = new ReversePostOrder(G);
    for(int v: RPO.getOrder()){
      if(!visited[v]){
        dfs(v,sccId++);
      }
    }
  }

  private void dfs(int s, int sccId){
    visited[s] = true;
    sccIds[s] = sccId;
    for(int adj: G.adj(s)){
      if(!visited[adj]){
        dfs(adj, sccId);
      }
    }
  }

  class ReversePostOrder{

    private  Digraph GR;
    boolean[] visited;
    Stack<Integer> reversePostOrder = new Stack<>();

    public ReversePostOrder(Digraph G) {
      this.GR = G.reverse();
      visited = new boolean[GR.V()];
      for(int v = 0; v < GR.V(); v++){
        if(!visited[v]){
          DFSReverseGraph(v);
        }
      }
    }

    public Iterable<Integer> getOrder(){
      return reversePostOrder;
    }

    private void DFSReverseGraph(int s){
      visited[s] = true;
      for(int adj: GR.adj(s)){
        if(!visited[adj]){
          DFSReverseGraph(adj);
        }
      }
      reversePostOrder.push(s);
    }
  }

  public static void main(String[] args) {
    In in = new In("/Users/abarik/pers/DS/src/main/java/Graph/DG/resource/tinyDG.txt");
    Digraph G = new Digraph(in);
    KosaRajuScc scc = new KosaRajuScc(G);

    // number of connected components
    int m = scc.countOfSCC();
    StdOut.println(m + " strong components");

/*    // compute list of vertices in each strong component
    Queue<Integer>[] components = (Queue<Integer>[]) new Queue[m];
    for (int i = 0; i < m; i++) {
      components[i] = new Queue<Integer>();
    }
    for (int v = 0; v < G.V(); v++) {
      components[scc.id(v)].enqueue(v);
    }

    // print results
    for (int i = 0; i < m; i++) {
      for (int v : components[i]) {
        StdOut.print(v + " ");
      }
      StdOut.println();
    }*/

  }

}
