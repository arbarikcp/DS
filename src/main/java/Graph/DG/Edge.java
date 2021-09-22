package Graph.DG;

import java.util.Comparator;

public class Edge {

  int from, to, weight;
  public static Comparator<Edge> EDGE_COMPARATOR = new EdgeWeightComparator();

  public Edge(int f, int t, int w) {
    from = f;
    to = t;
    weight = w;
  }

   static class EdgeWeightComparator implements Comparator<Edge>{

     @Override
     public int compare(Edge o1, Edge o2) {
       if(o1.weight == o2.weight) return 0;
       else if (o1.weight > o2.weight) return 1;
       else return 1;
     }
   }
}
