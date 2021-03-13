package Queue;

public class ArrayBasedQueue<T> implements Queue<T> {

  private static final int DEFAULT_SIZE = 1;

  private int head = -1;
  private int tail = -1;

  private T[] items;
  private int size;

  public ArrayBasedQueue() {
    this.items = (T[]) new Object[DEFAULT_SIZE];
  }

  @Override
  public void enqueue(T item) throws Exception {
    if (isFull()) {
      reSize();
    }

    tail = (++tail) % items.length;
    items[tail] = item;
    size++;

    if (head == -1) {
      head = 0;
    }
  }

  @Override
  public T dequeue() throws Exception {
    if (!isEmpty()) {
      head = ++head % items.length;
      size--;
      if(size < items.length / 4){
        shrink();
      }
      T itemToReturn  = items[head];
      items[head] = null;  // avoid loitering
      return itemToReturn;
    } else {
      head = tail = -1;
      throw new Exception("Underflow");
    }

  }

  @Override
  public T peek() {
    if (!isEmpty()) {
      return items[head];
    } else {
      return null;
    }
  }

  @Override
  public boolean isFull() {
    return (head == 0 && tail == items.length - 1) || (tail == head - 1);
  }

  @Override
  public boolean isEmpty() {
    return (head == tail);
  }

  private void reSize() {
    T[] newArray = (T[]) new Object[size * 2];
    int newIndex = 0;
    //Copy head to end of the array to new array
    for (int i = head; i < items.length; i++) {
      newArray[newIndex++] = items[i];
    }

    //copy 0 to tail to new array
    if(tail < head) {
      for (int i = 0; i <= tail; i++) {
        newArray[newIndex++] = items[i];
      }
    }
    head = 0; // head will be 0th position in new array
    tail = newIndex - 1; // tail will point to last element
    items = newArray;
  }

  private void shrink() {
    T[] newArray = (T[]) new Object[items.length / 2];
    int newIndex = 0;

    if(tail < head) {
      //Copy head to end of the array to new array
      for (int i = head; i < items.length; i++) {
        newArray[newIndex++] = items[i];
      }
      //copy 0 to tail to new array
      for (int i = 0; i <= tail; i++) {
        newArray[newIndex++] = items[i];
      }
    }else{
      //Copy head to end of the array to new array
      for (int i = head; i <= tail; i++) {
        newArray[newIndex++] = items[i];
      }
    }
    head = 0;
    tail = newIndex - 1;
    items = newArray;
  }
}
