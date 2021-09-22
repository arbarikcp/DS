package Graph.DG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class DijkstraSSSP {

  private static int INFINITY = Integer.MAX_VALUE;
  int size;
  boolean visited[];
  int dist[];
  int pathTo[];
  int start;
  private Map<Integer, List<Edge>> graph = new HashMap<>();

  private Comparator<NodeDistanceTuple> nodeDistanceTupleComparator = new Comparator<NodeDistanceTuple>() {
    @Override
    public int compare(NodeDistanceTuple o1, NodeDistanceTuple o2) {
      if( o1.weight == o2.weight) return 0;
      else if(o1.weight > o2.weight) return 1;
      else return -1;
    }
  };

  public DijkstraSSSP(Map<Integer, List<Edge>> graph, int startNode) {
    this.graph = graph;
    size = graph.size();
    visited = new boolean[size];
    dist = new int[size];
    pathTo = new int[size];
    Arrays.fill(dist, INFINITY);
    Arrays.fill(pathTo, INFINITY);
    start = startNode;
    getSSSP(startNode);
  }

  static class NodeDistanceTuple {
    int id;
    int weight;

    public NodeDistanceTuple(int id, int weight) {
      this.id = id;
      this.weight = weight;
    }
  }


  private void getSSSP(int start) {
    //Initialize the Priority Q with start node
    PriorityQueue<NodeDistanceTuple> edgeQueue = new PriorityQueue<>(nodeDistanceTupleComparator);
    edgeQueue.add(new NodeDistanceTuple(start, 0));
    dist[start] = 0;

    while (!edgeQueue.isEmpty()) {
      NodeDistanceTuple e = edgeQueue.poll(); // take next best edge to traverse, as edge are queued based on weight as priority
      visited[e.id] = true;
      List<Edge> adjEdges = graph.get(e.id); // get all adjacent edges of the node
      //Check all adjacent edges to unvisited nodes
      for (Edge edge : adjEdges) {
        if (!visited[edge.to]) {
          int newDist = Math.min(dist[edge.to], dist[e.id] + edge.weight); // calculate new distance, if we consider this edge.
          if (newDist < dist[edge.to]) { //If after taking this edge our path weight reduced from previous calculated path for this node, then take it.
            dist[edge.to] = newDist;
            pathTo[edge.to] = edge.from; //if we take this edge, then update the pathTo to this node by adding this edge.
            edgeQueue.add(new NodeDistanceTuple(edge.to,newDist));
          }
        }
      }
    }
  }

  public int[] getDistance(){return  dist;}

  public List<Integer> getPath(int dest){
    List<Integer> path = new ArrayList<>();
    if(dist[dest] != INFINITY ){ //check if dest node is reachable.
      path.add(dest);
      for(Integer at = pathTo[dest]; at != INFINITY; at = pathTo[at] ){
        path.add(at);
      }
      Collections.reverse(path);
    }
    return path;
  }

  public static void main(String[] args) {
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
    graph.get(2).add(new Edge(2, 7, 4));

    DijkstraSSSP dijkstraSSSP = new DijkstraSSSP(graph, 0);
    System.out.println(Arrays.toString(dijkstraSSSP.getDistance()));
    System.out.println("Path to 7="+ dijkstraSSSP.getPath(7));
  }
}
