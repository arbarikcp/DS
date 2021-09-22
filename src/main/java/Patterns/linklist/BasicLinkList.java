package Patterns.linklist;

import java.util.Queue;

public class BasicLinkList<T> {

  public static void main(String[] args) {
    BasicLinkList<Integer> bl = new BasicLinkList<>();
    for(int i = 0; i < 10; i++){
      bl.insert(i+1);
    }

/*    bl.getData();
    bl.reverse();
    bl.getData();*/

    //bl.reverse(1,10);
    bl.reverse(2);
    bl.getData();
  }

  Node start;
  Node last;

  class Node{
    T data;
    Node next;

    public Node(T data) {
      this.data = data;
      next = null;
    }

  }

  public void insert(T data){
    Node newNode = new Node(data);
    if(start == null){
      start = newNode;
      last = newNode;
    }else{
      last.next = newNode;
      last = newNode;
    }
  }

  public void getData(){
    Node current = start;
    System.out.println("");
    while (current != null){
      System.out.print(current.data+", ");
      current = current.next;
    }
  }

 public void reverse(){

    Node prev = null;
    Node current = start;
    Node next = null;

    while (current != null){
      next = current.next;
      current.next = prev;
      prev = current;
      current = next;
    }
    last = start;
    start = prev;
 }


 public void reverse(int p, int q){

    Node prev = null;
    Node current = start;

    for(int i = 0; current!= null && i < p-1; i++){
      prev = current;
      current = current.next;
    }

    Node lastNodeOfFirstPart = prev;
    Node lastNodeOfSecondPart = current;

    prev = null;
    Node next = null;

    for(int j =0; current!= null && j < q - p + 1; j++){
      next = current.next;
      current.next = prev;
      prev = current;
      current = next;
    }

    if(lastNodeOfFirstPart == null){
      start = prev;
    }else{
      lastNodeOfFirstPart.next = prev;
    }

    lastNodeOfSecondPart.next = next;
    if(next == null){
      last = lastNodeOfSecondPart;
    }

 }


 public void reverse( int k){

    Node prev = null;
    Node next = null;
    Node curr = start;
    Node newStartNode = null;
    while (curr != null) {
      Node lastNodeOfPart = curr;
      for (int i = 0; curr != null && i < k ; i++) {
        next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
      }
      lastNodeOfPart.next = next;
      if (newStartNode == null) {
        newStartNode = prev;
        start = newStartNode;
      }
    }
 }

}
