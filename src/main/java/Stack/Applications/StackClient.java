package Stack.Applications;

import java.util.Stack;

public class StackClient {

  public static void main(String[] args) {

    Infix expr = new Infix("( 1 * 2 + ( 3 - 42 ) + 57 )");
    expr.validateParenthesis();

    Stack<Integer> st = new Stack<>();
    for (int i = 0; i < 100; i++){
      st.push(i);
    }
    while (!st.empty()){
      System.out.println(st.pop());
    }

  }

}
