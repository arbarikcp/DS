package Patterns;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import sun.jvm.hotspot.utilities.Interval;

public class MergeInterval {

  public static void main(String[] args) {
    Interval i1 = new Interval(0,2);
    Interval i2 = new Interval(1,4);
    Interval i3 = new Interval(7,9);
    Interval i4 = new Interval(4,7);
    Interval i5 = new Interval(10,17);

    List<Interval> intervals = new LinkedList<>();
    //intervals.add(i1);
    intervals.add(i2);
    intervals.add(i3);
    intervals.add(i4);
    Collections.sort(intervals);
    insertInterval(intervals, i1);
    insertInterval(intervals, i5);

    int listSize = intervals.size();

    for(int i = 0; i< listSize -1;){
      Interval current = intervals.get(i);
      Interval next = intervals.get(i + 1);
      if(next.startTime <= current.endTime){
        Interval mergedInterval = current.merge(next);
        intervals.remove(i);
        intervals.remove(i);
        intervals.add(i,mergedInterval);
        listSize--;
      } else {
        i++;
      }
    }
    System.out.println("done");
  }

  private static void insertInterval(List<Interval> intervals, Interval newInterval){
    for(int i = 0; i< intervals.size(); i++){
      if(newInterval.startTime < intervals.get(i).startTime){
        intervals.add(i, newInterval);
        return;
      }
    }
    intervals.add(intervals.size(), newInterval);
  }

  private static void getIntersectionIntervals(List<Interval> first, List<Interval> second){
    int i= 0, j =0;

  }

  static   class Interval implements Comparable<Interval>{
    int startTime;
    int endTime;

    public Interval(int startTime, int endTime) {
      this.startTime = startTime;
      this.endTime = endTime;
    }


    Interval merge(Interval target){
      Interval interval = new Interval(startTime, Math.max(endTime, target.endTime));
      return interval;
    }

    static boolean isOverlapped(Interval i1, Interval i2){
      if((i1.startTime <= i2.startTime && i1.endTime >= i2.startTime) ||
          (i2.startTime <= i1.startTime && i2.endTime >= i1.startTime)){
        return true;
      }
      return false;
    }

    static Interval getIntersectionInterval(Interval i1, Interval i2){
      Interval first, second;
      if(i1.startTime < i2.startTime){
        first = i1;
        second = i2;
      }else{
        first = i2;
        second = i1;
      }
      if(first.endTime < second.startTime){
        return new Interval(Math.max(first.startTime, second.startTime),
            Math.min(first.endTime, second.endTime));
      }else{
        return null;
      }
    }

    @Override
    public int compareTo(Interval o) {
      return startTime - o.startTime;
    }
  }

  // sort the intervals List. by Collections.sort
  // iterate the list, check if the next interval overlaps the current one.
  //if overlaps, then take the 2 interval at index i, i+1 , merge them , delete interval at index i and i+1, insert the
  //new merged interval at index i.

}
