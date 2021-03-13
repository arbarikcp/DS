package Stack;

import java.util.Optional;

public class StackClient {

  public static void main(String[] args) throws Exception {

    //testStackListIter();
    testStackArrayIter();

/*    StackInterface<Integer> si = new ListBasedStack<>();
    StackInterface<Integer> ai = new ArrayBasedStack<>();
    for(int i = 0; i< 100; i++) {
      testStack(si);
      Thread.sleep(10000);
    }*/
  }


  private static void testStackArrayIter() throws Exception {
    ArrayBasedStack<String> li = new ArrayBasedStack<>();
    for(int i =0; i< 100; i++){
      li.push("abc"+String.valueOf(i));
    }

    for(String s: li){
      System.out.println(s);
    }

    for(String s: li){
      System.out.println(s);
    }
  }

  private static void testStackListIter() throws Exception {
  ListBasedStack<String> li = new ListBasedStack<>();
  for(int i =0; i< 100; i++){
    li.push("abc"+String.valueOf(i));
  }

  for(String s: li){
    System.out.println(s);
  }

    for(String s: li){
      System.out.println(s);
    }
  }

  private static void testStack(StackInterface<Integer> si) throws Exception {


    for(int item = 0; item < 1000000; si.push(item), item++){}

    System.out.println("Stack size="+ si.size());
    Optional<Integer> popedItem = si.pop();
    while (popedItem.isPresent()){
      System.out.println(popedItem.get());
      popedItem = si.pop();
    }
  }

}
