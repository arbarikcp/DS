package Queue;

import Stack.Node;

public class ListBasedQueue<T> implements Queue<T> {


  private Node<T> front;
  private Node<T> rear;


  @Override
  public void enqueue(T item) throws Exception {
    Node<T> newNode = new Node<T>(item);
    if(rear == null){
      rear = newNode;
      front = newNode;
    }else {
      rear.setNext(newNode);
      rear = newNode;
    }
  }

  @Override
  public T dequeue() throws Exception {

    if(front != null ){
      T item = front.getItem();
      front = front.getNext();
      return item;
    }else{
      throw new Exception("Underflow");
    }

  }

  @Override
  public T peek() {
    if(front != null){
      return front.getItem();
    }else{
      return null;
    }
  }

  @Override
  public boolean isFull() {
    return false;
  }

  @Override
  public boolean isEmpty() {
    return front == null;
  }
}
