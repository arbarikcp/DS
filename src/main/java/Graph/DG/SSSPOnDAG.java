package Graph.DG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SSSPOnDAG {

  static class Edge {
    int from, to, weight;

    public Edge(int f, int t, int w) {
      from = f;
      to = t;
      weight = w;
    }
  }

  private static int INFINITY = Integer.MAX_VALUE;
  private Map<Integer, List<Edge>> graph;
  private int verticesCount;
  private boolean visited[];
  private int[] ordering;
  private int orderIndex;
  private int[] dist;

  public SSSPOnDAG(Map<Integer, List<Edge>> graph) {
    this.graph = graph;
    verticesCount = graph.size();
    visited = new boolean[verticesCount];
    orderIndex = verticesCount;
    ordering = new int[verticesCount];
    dist = new int[verticesCount];
    for(int i = 0; i < verticesCount; i++){
      dist[i] = INFINITY;
    }
    topologicalSort();
  }

/*
    //moved to main
   int[] findLongestPathFrom(int start){
    Map<Integer, List<Edge>> negativeEdgeGraph = new HashMap<>();
    for(int key: graph.keySet()){
      List<Edge> negativeEdges = new ArrayList<>();
      for(Edge e : graph.get(key)){
        negativeEdges.add(new Edge(e.from, e.to, e.weight * -1));
      }
      negativeEdgeGraph.put(key, negativeEdges);
    }
    Map<Integer, List<Edge>> tempGraph = graph;
    graph = negativeEdgeGraph;
    int[] dist = findShortestPathFrom(start);
    for(int i = 0; i < dist.length; i++){
      dist[i] = dist[i] * -1;
    }
    graph = tempGraph;
    return dist;
  }*/

  int[] findShortestPathFrom(int start){
    dist[start] = 0;
    for(int i = 0; i < verticesCount ; i++){
      int currentNode = ordering[i];
      if(dist[currentNode] != INFINITY){
        for(Edge e : graph.get(currentNode)){
          int newDist = dist[currentNode] + e.weight;// if we take this edge, new distance to this node will be dist of start node + edge weight to reach there.
          dist[e.to] = Math.min(dist[e.to], newDist); // if existing distance to the node is less or more, take the lesser.
        }
      }
    }
    return dist;
  }

  int[] topologicalSort(){
    for(int start = 0; start < graph.size() ; start++){
      if (!visited[start]) {
        dfs(start);
      }
    }
    return ordering;
  }

  void dfs(int currentNode){
    visited[currentNode] = true;
    for(Edge e : graph.get(currentNode)){
      if(!visited[e.to]){
        dfs(e.to);
      }
    }
    ordering[--orderIndex] = currentNode;
  }



  public static void main(String[] args) {
    //graph setup
    final int N = 8;
    Map<Integer, List<Edge>> graph = new HashMap<>();
    for (int i = 0; i < N; i++) graph.put(i, new ArrayList<>());
    graph.get(0).add(new Edge(0, 1, 3));
    graph.get(0).add(new Edge(0, 2, 6));
    graph.get(1).add(new Edge(1, 2, 4));
    graph.get(1).add(new Edge(1, 3, 4));
    graph.get(1).add(new Edge(1, 4, 11));
    graph.get(2).add(new Edge(2, 3, 8));
    graph.get(2).add(new Edge(2, 6, 11));
    graph.get(3).add(new Edge(3, 4, -4));
    graph.get(3).add(new Edge(3, 5, 5));
    graph.get(3).add(new Edge(3, 6, 2));
    graph.get(4).add(new Edge(4, 7, 9));
    graph.get(5).add(new Edge(5, 7, 1));
    graph.get(6).add(new Edge(6, 7, 2));

    SSSPOnDAG ssspOnDAG = new SSSPOnDAG(graph);
    //int[] topSortOrder = ssspOnDAG.topologicalSort();
    // System.out.println(Arrays.toString(topSortOrder));
    System.out.println(Arrays.toString(ssspOnDAG.findShortestPathFrom(0)));

    //Find the longest path in the graph from a starting node
    // -> Create a -ve edge graph of the given graph.
    // -> Find the shortest path dist[] from the given node.
    // -> Then multiple every element of dist[] with -1.

    Map<Integer, List<Edge>> negativeEdgeGraph = new HashMap<>();
    for(int key: graph.keySet()){
      List<Edge> negativeEdges = new ArrayList<>();
      for(Edge e : graph.get(key)){
        negativeEdges.add(new Edge(e.from, e.to, e.weight * -1));
      }
      negativeEdgeGraph.put(key, negativeEdges);
    }

    SSSPOnDAG sslpOnDAG = new SSSPOnDAG(negativeEdgeGraph);
    int[] dist = sslpOnDAG.findShortestPathFrom(0);
    for(int i = 0; i < dist.length; i++){
      dist[i] = dist[i] * -1;
    }

    System.out.println(Arrays.toString(dist));

  }
}
