package Graph.UG;

import java.math.BigInteger;

public class GraphClient {

  public static void main(String[] args) {

    Graph g = new Graph(9);
    g.addEdge(0, 3);
    g.addEdge(0, 2);
    g.addEdge(4, 1);
    g.addEdge(1, 3);
    g.addEdge(4, 0);
    g.addEdge(4, 4);
    g.addEdge(3, 5);
    g.addEdge(7,8);
    //g.addEdge(1,6);

    for (int v = 0; v < g.verticesCount(); v++) {
      System.out.println(g.adj(v));
    }
    System.out.println("max degree="+ g.maxDegree());
    System.out.println("Number of self loop "+ g.numberOfSelfLoop());
    DepthFirstPath df = new DepthFirstPath(g,0);
    System.out.println(df.hasPathTo(5));
    System.out.println(df.pathTo(5));

    BreadthFirstSearch bf = new BreadthFirstSearch(g, 0);
    System.out.println(bf.hasPathTo(5));
    System.out.println(bf.pathTo(5));

    ConnectedComponents cc = new ConnectedComponents(g);
    System.out.println("connected components count="+ cc.count());
    System.out.println("1,6 are connected ? "+ cc.isConnected(1,6));
    System.out.println("cc id of 7 is "+ cc.getCCId(7));

    testBipartite();
    testCycleDetection();
  }

  public  static void testBipartite(){

    Graph g = new Graph(7);
    g.addEdge(0,1);
    g.addEdge(0,2);
    g.addEdge(0,5);
    g.addEdge(0,6);
    g.addEdge(1,3);
    g.addEdge(2,3);
    g.addEdge(2,4);
    g.addEdge(4,5);
    g.addEdge(4,6);
    //g.addEdge(1,6);
    //g.addEdge(1,5);

    Bipartite bp = new Bipartite(g);
    System.out.println("is bipartite ?"+ bp.isBipartite());
  }

  private static void testCycleDetection(){

    CycleDetection cd = new CycleDetection(createAcyclicGraph());
    System.out.println("is cycle exists "+ cd.hasCycle());
  }

  private static  Graph createGraph(){
    Graph g = new Graph(7);
    g.addEdge(0,1);
    g.addEdge(0,2);
    g.addEdge(0,5);
    g.addEdge(0,6);
    g.addEdge(1,3);
    g.addEdge(2,3);
    g.addEdge(2,4);
    g.addEdge(4,5);
    g.addEdge(4,6);
    //g.addEdge(1,6);
    //g.addEdge(1,5);
    return g;
  }

  private static  Graph createAcyclicGraph(){
    Graph g = new Graph(7);
    g.addEdge(0,1);
    g.addEdge(1,2);
    g.addEdge(2,3);
    g.addEdge(3,4);
    g.addEdge(4,5);
    //g.addEdge(4,5);
    g.addEdge(5,6);
    g.addEdge(6,0);
    return g;
  }

}
