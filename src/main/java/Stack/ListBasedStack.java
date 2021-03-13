package Stack;

import java.util.Iterator;
import java.util.Optional;

public class ListBasedStack<T> implements StackInterface<T>, Iterable<T> {


  private Node<T> top;
  private int size;

  @Override
  public void push(T item) throws Exception {
    if (item == null) {
      return;
    }
    Node<T> newNode = new Node<T>(item);
    newNode.setNext(top);
    top = newNode;
    size++;
  }

  @Override
  public Optional<T> pop() throws Exception {
    if (top == null) {
      return Optional.empty();
    }
    T item = top.getItem();
    top = top.getNext();
    size--;
    return Optional.ofNullable(item);
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public Iterator<T> iterator() {
    return new ListBasedStackIter();
  }

  public class ListBasedStackIter implements Iterator<T> {


    private Node<T> current = top;

    @Override
    public boolean hasNext() {
      return current != null;
    }

    @Override
    public T next() {
      T item = current.getItem();
      current = current.getNext();
      return item;
    }
  }
}
