package Graph.DG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FloydWarshall {

  static double POSITIVE_INFINITY = Double.POSITIVE_INFINITY;
  static double NEGATIVE_INFINITY = Double.NEGATIVE_INFINITY;
  static int NEGATIVE_CYCLE_REACHED = -1;

  double [][] graph ;
  double[][] dp;
  Integer[][] next;

  public FloydWarshall(double[][] graph) {
    this.graph = graph;
    dp = new double[graph.length][graph.length];
    next = new Integer[graph.length][graph.length];

    for(int i = 0; i < graph.length; i++){
      for(int j = 0; j <graph.length; j++){
        dp[i][j] = graph[i][j];
        if(graph[i][j] != POSITIVE_INFINITY){
          next[i][j] = j;
        }
      }
    }
    runFloydWarshallAlgo();
  }

  public void runFloydWarshallAlgo(){
    int V = graph.length;
    for(int k = 0; k < V; k ++){
      for(int i = 0; i < V; i++){
        for(int j = 0; j < V; j++){
          Double newDist = dp[i][k] + dp[k][j];
          if(newDist < dp[i][j]){
            dp[i][j] = newDist;
            next[i][j] = next[i][k];
          }
        }
      }
    }

    //Check -ve cycle
    for(int k = 0; k < V; k ++){
      for(int i = 0; i < V; i++){
        for(int j = 0; j < V; j++){
          Double newDist = dp[i][k] + dp[k][j];
          if(newDist < dp[i][j]){
            dp[i][j] = NEGATIVE_INFINITY;
            next[i][j] = NEGATIVE_CYCLE_REACHED;
          }
        }
      }
    }
  }

  public double[][] getAPSPMatrix(){
    return  dp;
  }

  public Integer[][] getPathMatrix(){
    return next;
  }

  public boolean isPathExists(int start, int end){
    return next[start][end] != NEGATIVE_CYCLE_REACHED && next[start][end] != null;
  }

  public List<Integer> getPath(int start, int end){
    List<Integer> path = new ArrayList<>();
    if(isPathExists(start, end)){
      int at = start;
        for(; at != end; at = next[at][end]){
          if(at == NEGATIVE_CYCLE_REACHED){
            return null;
          }
          path.add(at);
        }

        if(next[at][end] == NEGATIVE_CYCLE_REACHED){
          return null;
        }else{
          path.add(end);
        }
      return path;
    }else{
      return path;
    }
  }

  public static void main(String[] args) {

    int n = 7;
    double[][] m = new double[n][n];
    for(int i = 0; i < n ; i++){
      Arrays.fill(m[i], POSITIVE_INFINITY );
      m[i][i] = 0;
    }

    // Add some edge values.
    m[0][1] = 2;
    m[0][2] = 5;
    m[0][6] = 10;
    m[1][2] = 2;
    m[1][4] = 11;
    m[2][6] = 2;
    m[6][5] = 11;
    m[4][5] = 1;
    m[5][4] = 2;

    FloydWarshall fw = new FloydWarshall(m);
    double[][] apspMatrix =  fw.getAPSPMatrix();
    for(int i = 0; i< apspMatrix.length; i++){
      System.out.println(Arrays.toString(apspMatrix[i]));
    }

    Integer[][] pathMatrix = fw.getPathMatrix();
    for(int i = 0; i < pathMatrix.length; i++){
      System.out.println(Arrays.toString(pathMatrix[i]));
    }
    System.out.println(fw.getPath(0,6));
  }
}
