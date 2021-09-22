package Maximal.Fundamental;

public class Tuple {

    int first, next;

    public Tuple(int first, int next) {
      this.first = first;
      this.next = next;
    }

    public void setFirst(int first) {
      this.first = first;
    }

    public void setNext(int next) {
      this.next = next;
    }

    public int getFirst() {
      return first;
    }

    public int getNext() {
      return next;
    }
}
