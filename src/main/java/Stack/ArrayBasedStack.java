package Stack;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Optional;

public class ArrayBasedStack<T> implements StackInterface<T>, Iterable<T> {

  private static final int DEFAULT_SIZE = 1;
  private T[] items;
  private int size;

  public ArrayBasedStack() {
    this.items = (T[]) new Object[DEFAULT_SIZE];
  }

  @Override
  public void push(T item) throws Exception {
    if (item == null) {
      return;
    }
    if (size == this.items.length) {
      reSize();
    }
    this.items[size++] = item;
  }

  @Override
  public Optional<T> pop() throws Exception {
    if (size < items.length / 4) {
      shrink();
    }
    if (size > 0) {
      T currentItem = items[--size];
      items[size] = null;  // Set the value to null to avoid loitering.
      return Optional.of(currentItem);
    }
    return Optional.empty();
  }

  @Override
  public int size() {
    return size;
  }

  private void reSize() {
    items = Arrays.copyOf(items.clone(), size * 2);
/*    T[] newArray = Arrays.copyOf(items.clone(), size * 2);
    items = null;
    items = newArray;*/
  }

  private void shrink() {
    items = Arrays.copyOf(items.clone(), items.length / 2);
/*   T[] newArray = Arrays.copyOf(items.clone(), items.length / 2);
    items = null;
    items = newArray;*/
  }

  @Override
  public Iterator<T> iterator() {
    return new ArrayBasedStackIter();
  }

  private class ArrayBasedStackIter implements Iterator<T> {

    int currentIndex = size;

    @Override
    public boolean hasNext() {
      return currentIndex > 0;
    }

    @Override
    public T next() {

      return items[--currentIndex];
    }
  }

}
