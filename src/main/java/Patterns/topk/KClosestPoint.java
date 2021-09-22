package Patterns.topk;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KClosestPoint {

  public static void main(String[] args) {

    int k = 20;
    List<Point> points = new ArrayList<>();
    points.add(new Point(1, 2));
    points.add(new Point(1, 5));
    points.add(new Point(1, 4));
    points.add(new Point(1, 10));
    points.add(new Point(1, 5));
    points.add(new Point(1, 3));

    PriorityQueue<Point> minHeap =
        new PriorityQueue<>((p1, p2) -> p2.distanceFromOrigin - p1.distanceFromOrigin);
    for (Point p : points) {
      int size = minHeap.size();
      if (size < k) {
        minHeap.add(p);
      } else if (minHeap.peek().compareTo(p) > 0) {
        minHeap.poll();
        minHeap.add(p);
      }
    }

    System.out.println("done");
  }

  static class Point implements Comparable<Point> {
    int x;
    int y;
    int distanceFromOrigin;

    public Point(int x, int y) {
      this.x = x;
      this.y = y;
      this.distanceFromOrigin = getDistanceFromOrigin();
    }

    int getDistanceFromOrigin() {
      return (int) (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
    }

    @Override
    public int compareTo(Point o) {
      return distanceFromOrigin - o.distanceFromOrigin;
    }
  }
}
