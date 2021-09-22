package MonotonicQueue;

import java.util.Arrays;
import java.util.Stack;

public class FindNextBigNumber {

  public static int[] getResult(int a[]){

    Stack<Integer> st = new Stack<>();
    int n = a.length;
    int[] result = new int[a.length];
    for (int i = n - 1 ; i >= 0 ; i-- ){
      //pop all the elements from stack which are smaller than current element
      while (!st.empty() && a[st.peek()] < a[i]) st.pop();
      //After above step,
      // either we will have an empty stack or top of element of stack is greater than the current element.
      if(st.isEmpty()){
        result[i] = i;
      }else{
        result[i] = st.peek();
      }
      st.push(i);
    }
    return result;
  }

  public static void main(String[] args) {
    int[] temp = {89,62,70,113,47,76,100,120}; //[3,2,3,7,5,6,7,7]
    System.out.println(Arrays.toString(getResult(temp)));
  }
}
