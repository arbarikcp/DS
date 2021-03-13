package Stack;

public class Node<T> {

  private T item;
  private Node<T> next;

  public Node(T item) {
    this.item = item;
  }

  public T getItem() {
    return item;
  }

  public Node<T> getNext() {
    return next;
  }

  public void setNext(Node<T> next) {
    this.next = next;
  }
}
