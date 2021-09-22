package Graph.DG;

import Tree.StdOut;
import java.util.Stack;

public class TopSort2 {

  private Digraph g;
  private int[] visitState; // 0 = unvisited, 1 = in dfs stack, 2 = completed
  private Stack<Integer> ordering;
  private int count;
  private boolean hasCycle;

  public TopSort2(Digraph g) {
    this.g = g;
    count = g.V();
    visitState = new int[count];
    ordering = new Stack<>();
    for (int v = 0; v < count; v++)
      if (visitState[v] != 2 && !hasCycle) {
         hasCycle = dfs_with_cycle_detection(v);
    }
  }

  private boolean dfs_with_cycle_detection(int currentNode){
    //This is a side edge, this node has been processed as part of some dfs (starting from some other node)
    if(visitState[currentNode] == 2){
      return false;
    }

    //This is a back edge. It means this node currently on recursion stack, we got another node
    //which pointing to this node. So it is a cycle.
    if(visitState[currentNode] == 1){
      return true; //cycle detected
    }

    visitState[currentNode] = 1; // node is currently on the recursion stack
    for (int adjNode : g.adj(currentNode)){
        if(dfs_with_cycle_detection(adjNode))
          return true;
    }
    visitState[currentNode] = 2; // Completed processing of the node
    ordering.push(currentNode);
    return false;
  }

  private Iterable<Integer> getOrdering(){
    return ordering;
  }

  public static void main(String[] args) {

    TopSort2 ts2 = new TopSort2(DigraphGenerator.cycle(10));
    if (!ts2.hasCycle) {
      for (int v : ts2.getOrdering()) {
        StdOut.println(v);
      }
    } else{
      System.out.println("Cycle detected");
    }


    TopSort2 ts = new TopSort2(DigraphGenerator.dag(10, 25));
    if (!ts.hasCycle) {
      for (int v : ts.getOrdering()) {
        StdOut.println(v);
      }
    } else{
      System.out.println("Cycle detected");
    }
  }
}
