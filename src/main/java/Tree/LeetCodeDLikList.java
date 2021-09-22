package Tree;

public class LeetCodeDLikList {

  class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
  }

  public Node flatten(Node head) {

    return flattenTheList(head,null);
  }

  private Node copyNode(Node src){
    Node target = new Node();
    target.val = src.val;
    return target;
  }
  private Node flattenTheList(Node curr, Node prev){
    if (curr != null) {
      Node target = copyNode(curr);
      target.prev = prev;
      if(curr.child != null){
        target.next = flattenTheList(curr.child, target);
        target = target.next;
      } else if (curr.next != null) {
        target.next = flattenTheList(curr.next, target);
      }
      return target;
    } else{
      return null;
    }
  }

}
