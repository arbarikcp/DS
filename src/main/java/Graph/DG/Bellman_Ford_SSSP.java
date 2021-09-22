package Graph.DG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bellman_Ford_SSSP {

  private Map<Integer, List<Edge>> G;
  private List<Edge> edges = new ArrayList<>();
  private double dist[];

  public Bellman_Ford_SSSP(Map<Integer, List<Edge>> g, int start) {
    G = g;
    dist = new double[g.size()];
    Arrays.fill(dist, Double.POSITIVE_INFINITY);
    dist[start] = 0;
    //Get all the edges of the graph in the list
    for(int v = 0; v < g.size(); v++){
      edges.addAll(g.get(v));
    }
    runBellmanFordAlgo(start);
  }

  private void runBellmanFordAlgo(int s){

    for(int i = 0; i < G.size(); i++){
      // For each vertex, apply relaxation for all the edges
      for(Edge e: edges){ // in each iteration, We need to relax all the edges. (NOT just adj Edges)
         relaxEdge(e);
      }
    }// At end of this loop, we will get the SSSP dist array for S. Then check if there is a -ve cycle.

    // Run algorithm a second time to detect which nodes are part
    // of a negative cycle. A negative cycle has occurred if we
    // can find a better path beyond the optimal solution.
    for(int i = 0; i < G.size(); i++){
      for(Edge e: edges){
        if (relaxEdge(e)) { // if still the edge is relaxed, then it is a -ve cycle
          System.out.println("Affected by -ve cycle");
          dist[e.to] = Double.NEGATIVE_INFINITY; // node is in -ve cycle or affected by -ve cycle
        }
      }
    }
  }

  private boolean relaxEdge(Edge e){
    double newDistTot = dist[e.from] + e.weight;
    if(newDistTot < dist[e.to]){
      dist[e.to] = newDistTot;
      return true;
    }else{
      return false;
    }
  }
  public double[] getDist(){return  dist;}

  public static void main(String[] args) {

    final int N = 8;
    Map<Integer, List<Edge>> graph = new HashMap<>();
    for (int i = 0; i < N; i++) graph.put(i, new ArrayList<>());
    graph.get(0).add(new Edge(0, 1, 1));
    graph.get(1).add(new Edge(1, 2, 1));
    graph.get(2).add(new Edge(2, 4, 1));
    graph.get(4).add(new Edge(4, 3, -3));
    graph.get(3).add(new Edge(3, 2, 1));
    graph.get(1).add(new Edge(1, 5, 4));
    graph.get(1).add(new Edge(1, 6, 4));
    graph.get(5).add(new Edge(5, 6, 5));
    graph.get(6).add(new Edge(6, 7, 4));
    graph.get(5).add(new Edge(5, 7, 3));

    Bellman_Ford_SSSP bf = new Bellman_Ford_SSSP(graph, 0);
    System.out.println(Arrays.toString(bf.dist));
  }
}
