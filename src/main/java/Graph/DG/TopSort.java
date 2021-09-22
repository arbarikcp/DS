package Graph.DG;

import Graph.UG.SymbolDigraph;
import Tree.StdOut;

public class TopSort {

  private Digraph g;
  private boolean[] visited;
  private int[] ordering;
  private int count;
  private int index;

  /*
  The issue with this topsort is that it doesn't detect the cycle. So the input must be a DAG.
  If input is a  Cyclic DG then there is no top sort order exists but this class will generate a wrong one.
   */
  public TopSort(Digraph g) {
    this.g = g;
    count = g.V();
    index = count;
    visited = new boolean[count];
    ordering = new int[count];
    for (int v = 0; v < count; v++)
      if (!visited[v]) {
        dfs(v);
    }
  }

  private void dfs(int currentNode){
    //System.out.println("starting "+ currentNode);
    visited[currentNode] = true;
    for (int adjNode : g.adj(currentNode)){
      if(!visited[adjNode]){
        dfs(adjNode);
        //System.out.println("--------> done with "+ adjNode );
        //ordering[--index] = currentNode; //why this wrong? this will miss all starting nodes, it will only take the adjacent nodes
      }
    }
    ordering[--index] = currentNode;
  }

  private int[] getOrdering(){
    return ordering;
  }

  public static void main(String[] args) {

    String filename  = "/Users/abarik/pers/DS/src/main/java/Graph/DG/resource/jobs.txt";
    String delimiter = "/";
    SymbolDigraph sg = new SymbolDigraph(filename, delimiter);
    System.out.println(sg);
    TopSort ts = new TopSort(sg.digraph());
    for (int v : ts.getOrdering()) {
      StdOut.println(sg.nameOf(v));
    }
  }
}
