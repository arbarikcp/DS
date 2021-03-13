package Queue;

public class QueueClient {

  public static void main(String[] args) throws Exception {

    ListBasedQueue<Integer> li = new ListBasedQueue<Integer>();
    ArrayBasedQueue<Integer> aq = new ArrayBasedQueue<Integer>();

    for(int i = 0; i < 100; aq.enqueue(i),i++){}

    Integer current = aq.dequeue();
    while (current != null){
      System.out.println(current);
      current = aq.dequeue();
    }

  }

}
