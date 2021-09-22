package Graph.DG;

import Tree.StdOut;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class TopSort_kahn {

  private Digraph g;
  private int[] inDegrees;
  private Queue<Integer> queue;
  private Stack<Integer> ordering;
  private boolean[] visited;

  private int count;
  private boolean hasCycle;

  public TopSort_kahn(Digraph g) {

    this.g = g;
    count = g.V();
    visited = new boolean[count];
    inDegrees = new int[count];
    //queue = new ArrayDeque<>();
    queue = new PriorityQueue<>();  //If we want to have nodes in
    ordering = new Stack<>();
    for (int v = 0; v < count; v++)
    {
      inDegrees[v] = g.indegree(v);
      if(inDegrees[v] == 0){ //In-degree 0 means this vertex is not dependent on any other vertex
        queue.add(v); // Initialize the Queue with all the vertices which doesn't have any dependency. SO they can be picked first.
      }
    }
    traverse();
    for(int v =0; v < count; v++){
      if(!visited[v]){
        System.out.println("There is a cycle");
      }
    }
  }

  private void traverse(){

    while (!queue.isEmpty()){
      Integer currentVertex = queue.poll();
      visited[currentVertex] = true;
      ordering.push(currentVertex); // once we have finished this node, Think like we have completed one task.
      for(int adj: g.adj(currentVertex)){ // Then dependency of all its neighbour has been reduced by 1. So reduce in-degree of all the adjacent node by 1.
        inDegrees[adj] = inDegrees[adj] - 1;
        if(inDegrees[adj] == 0){ // After reducing the dependency if Some nodes in-degree become 0, it means all its dependency has been fulfilled. So it can be picked now.
          queue.add(adj);
        }
      }
    }
  }

  private Iterable<Integer> getOrdering(){
    return ordering;
  }

  public static void main(String[] args) {

    Digraph dg = new Digraph(7);
    dg.addEdge(0,1);
    dg.addEdge(0,2);
    dg.addEdge(1,2);
    dg.addEdge(2,3);
    dg.addEdge(4,5);
    dg.addEdge(6,0);
    dg.addEdge(5,4);

    TopSort_kahn topSort_kahn = new TopSort_kahn(dg);
    for (int v : topSort_kahn.getOrdering()) {
      StdOut.println(v);
    }

/*    TopSort_kahn ts2 = new TopSort_kahn(DigraphGenerator.cycle(4));
    if (!ts2.hasCycle) {
      for (int v : ts2.getOrdering()) {
        StdOut.println(v);
      }
    } else{
      System.out.println("Cycle detected");
    }*/


/*    TopSort_kahn ts = new TopSort_kahn(DigraphGenerator.dag(5, 8));
    if (!ts.hasCycle) {
      for (int v : ts.getOrdering()) {
        StdOut.println(v);
      }
    } else{
      System.out.println("Cycle detected");
    }*/
  }
}
