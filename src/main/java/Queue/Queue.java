package Queue;

public interface Queue<T> {

  void enqueue(T item) throws Exception;

  T dequeue() throws Exception;

  T peek();

  boolean isFull();

  boolean isEmpty();

}
