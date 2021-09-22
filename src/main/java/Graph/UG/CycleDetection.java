package Graph.UG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class CycleDetection {

  private Graph g;
  private boolean[] visited;
  private int[] edgeTo ;
  private boolean cycleExists =  false;
  private List<ArrayList<Integer>> cycles = new ArrayList<>();

  public CycleDetection(Graph g) {
    this.g = g;
    visited = new boolean[g.verticesCount()];
    edgeTo = new int[g.verticesCount()];
    for(int i = 0; i < g.verticesCount(); i++){
      edgeTo[i] = -1;
    }
    if(hasParallelEdge_1()){
      cycleExists = true;
    } else {
      dfs(0, 0);
    }
  }

  public boolean hasCycle(){
    return cycleExists;
  }

  private void dfs(int currentNode, int parent){
    visited[currentNode] = true;
    for(int adjNode : g.adj(currentNode)){
      if(!visited[adjNode]){
        dfs(adjNode,currentNode);
        edgeTo[adjNode] = currentNode;
      }else{ //if the node is already visited, and it is not the current's parent node.
        if (adjNode != parent) {
          System.out.println("detect a cycle while checking edge "+parent +"--"+ currentNode);
          cycleExists = true;
          return;
        }
      }
    }
  }

  private ArrayList getTheCycle(int dest){
    ArrayList<Integer> cycle =  new ArrayList();
    cycle.add(dest);
    while (edgeTo[dest] != dest){
      cycle.add(edgeTo[dest]);
      dest = edgeTo[dest];
    }
    Collections.reverse(cycle);
    return cycle;
  }

  private  boolean hasParallelEdge(){
    boolean[] marked = new boolean[g.verticesCount()]; // have an array to mark all neighbour
    for(int v = 0; v < g.verticesCount(); v++){
      for(int w: g.adj(v)){
        if(marked[w]){ // this neighbour already visited from this node.
          System.out.println("parallel edge exists "+ v +"--"+ w);
          return true;
        }
        marked[w] = true; // set the neighbour to true.
      }
      for(int w: g.adj(v)){
        marked[w] = false; // reset all the neighbour of current node
      }
    }
    return false;
  }

  private  boolean hasParallelEdge_1(){
    for(int v = 0; v < g.verticesCount(); v++){
      Set<Integer> neighbors = new HashSet<>();
      for(int w: g.adj(v)){
        if(neighbors.contains(w)){
          System.out.println("There is a parallel edge between "+ v +"--"+ w);
          return true;
        }else{
          neighbors.add(w);
        }
      }
    }
    return false;
  }

}
