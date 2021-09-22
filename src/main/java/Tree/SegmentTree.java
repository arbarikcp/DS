package Tree;

import java.util.Arrays;
import utils.In;

public class SegmentTree {

  int[] segTree;
  int [] arr;
  Operation<Integer> operation;

  public SegmentTree(int[] arr, Operation operation) {
    this.arr = arr;
    this.operation = operation;
    this.segTree = new int[getSegTreeSize(arr.length)];
    Arrays.fill(segTree, (Integer) operation.initialValue());
    build(0, arr.length - 1, 0);
    System.out.println("arr="+ Arrays.toString(arr));
    System.out.println("segTree= " + Arrays.toString(segTree));
  }

  void build(int low, int high, int segTreePos)
  {
    if(low == high){
      segTree[segTreePos] = arr[low];
      return;
    }
    int mid = (low + high) /2;
    build(low, mid, 2*segTreePos +1);
    build(mid+1, high, 2*segTreePos +2);
    segTree[segTreePos] = operation.applyOperation(segTree[2*segTreePos +1] , segTree[2*segTreePos +2]);
  }

  public int query(int start, int end){
    return query(0, 0, arr.length-1,start, end);
  }

  //segTree[segTreePos] is responsible for arr[segTreeLowRange] to arr[segTreeHighRange]
  //segTree[0] is responsible for arr[0] to arr[arr.length -1]
  //segTree[1] is responsible for arr[0] to arr[mid]
  //segTree[2] is responsible for arr[mid+1] to arr[arr.length -1]

  private int query(int segTreePos, int segTreeLowRange, int segTreeHighRange, int queryLow, int queryHigh){

    //Total overlap
    if(queryLow <= segTreeLowRange   && queryHigh >= segTreeHighRange){
      return segTree[segTreePos];
    }
    //no overlap
    if(queryLow >segTreeHighRange || queryHigh < segTreeLowRange){
      return 0;
    }
    int mid = (segTreeHighRange + segTreeLowRange) /2;
    int lChild = 2*segTreePos+1;
    int rChild = 2*segTreePos+2;
    //partial overlap , chek in both side of the tree
    return operation.applyOperation( query(lChild,segTreeLowRange, mid, queryLow, queryHigh),
        query(rChild, mid+1, segTreeHighRange, queryLow, queryHigh));
  }

  private int getSegTreeSize(int arrSize){
    return nearestPowerOf2(arrSize) - 1;
  }
  private int nearestPowerOf2(int x){
    return Integer.highestOneBit(x) * 2;
  }

  public void updateValue(int index, int newValue){
    int delta = newValue - arr[index];
  }

  private void updateSegTree(int segTreePos, int segTreeLow, int segTreeHigh, int index, int delta){
    if(index < segTreeLow || index > segTreeHigh){
      return;
    }
    if(segTreeLow == segTreeHigh && segTreeHigh == index){
      segTree[segTreePos] = segTreePos + delta;
    }
    int mid = (segTreeHigh + segTreeLow) / 2;
    int lChild = 2 * segTreePos + 1;
    int rChild = 2 * segTreePos + 2;
    /*segTree[segTreePos] = operation.applyOperation(
      updateSegTree(mid);
    );*/
  }


  public static void main(String[] args) {
    int[] arr = {3,7,2,4,5,6,8,10};
    //int[] arr = {3,7,2,4};
    //SegmentTree st = new SegmentTree(arr, new SumOperation());
    SegmentTree st = new SegmentTree(arr, new MaxOperation());
    System.out.println("result ="+ st.query(0,4));
  }
}

interface  Operation<T>{

  T applyOperation(T a, T b);
  T initialValue();
}


class SumOperation implements Operation<Integer>{

  @Override
  public Integer applyOperation(Integer a, Integer b) {
    return a + b;
  }

  @Override
  public Integer initialValue() {
    return 0;
  }
}
class MinOperation implements Operation<Integer>{

  @Override
  public Integer applyOperation(Integer a, Integer b) {
    return Math.min( a, b);
  }

  @Override
  public Integer initialValue() {
    return Integer.MAX_VALUE;
  }
}

class MaxOperation implements Operation<Integer>{

  @Override
  public Integer applyOperation(Integer a, Integer b) {
    return Math.max( a, b);
  }

  @Override
  public Integer initialValue() {
    return Integer.MIN_VALUE;
  }
}