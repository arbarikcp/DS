package Interview.List;

public class LinkedList {

  LinkNode head;
  LinkNode tail;

  public LinkedList() {
    this.head = null;
  }

  public static void main(String[] args) {
    int[] arr = new int[]{3,5,6,1,7,8,2,8,9,10};
    LinkedList list =   createSortedLinkedList(arr);
    traverse(list.head);
  }

  public static LinkedList createLinkedList(int[] arr){
    LinkedList list = new LinkedList();
    for(int i : arr){
      list.insertAtEnd(i);
    }
    return list;
  }

  public static LinkedList createSortedLinkedList(int[] arr){
    LinkedList list = new LinkedList();
    for(int i : arr){
      list.sortedInsert(i);
    }
    return list;
  }

  public void insertAtStart(int key){
    LinkNode node = new LinkNode(key);
    if(head == null){ head = node; tail = node;}
    else{node.next = head; head = node; }
  }

  public void insertAtEnd(int key){
    LinkNode node = new LinkNode(key);
    if(head == null) { head = node; tail = node;}
    else{tail.next = node;tail = node;}
  }

  public void insertAfter(LinkNode node, int key){
    if(node != null){
      LinkNode temp = node.next;
      node.next = new LinkNode(key);
      node.next.next = temp;
    }
  }

  public void sortedInsert(int key){
    LinkNode node = new LinkNode(key);
    if(head == null) { head = node; tail = node;}
    else{
      LinkNode curr = head;
      LinkNode prev = null;
      while (curr != null && curr.key < key){ //Find the position to insert
        prev = curr; //Always keep the reference  of current before moving
        curr = curr.next;
      }
      if(prev == null){ //Insert at beginning
        insertAtStart(key);
      }else{ //Insert at position after the node pointed by prev
        insertAfter(prev, key);
      }
    }
  }

  public void reverse(){

  }

  public static void  traverse(LinkNode head){
    LinkNode curr = head;
    while (curr != null){
      System.out.print(curr.key+"->");
      curr = curr.next;
    }
    System.out.print("null");
  }


  static class LinkNode{
    int key;
    LinkNode next;

    public LinkNode(int key) {
      this.key = key;
      this.next = null;
    }
  }

}
