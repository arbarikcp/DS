package MonotonicQueue;

import java.util.Arrays;
import java.util.Stack;

public class LargestRectangleInHistogram {

  private static int[] findNextSmallElement(int a[]) {
    Stack<Integer> st = new Stack<>();
    int n = a.length;
    int[] nextSmallElement = new int[n];
    for (int i = n - 1; i >= 0; i--) { // iterate the array from right to left
      while (!st.isEmpty() && a[st.peek()] > a[i]) st.pop();
      if (st.isEmpty()) {
        nextSmallElement[i] = n;
      } else {
        nextSmallElement[i] = st.peek();
      }
      st.push(i);
    }
    return nextSmallElement;
  }

  private static int[] findLastSmallElement(int a[]) {
    Stack<Integer> st = new Stack<>();
    int n = a.length;
    int[] lastSmallElement = new int[n];
    for (int i = 0; i < n; i++) { //iterate the array from left to right
      while (!st.isEmpty() && a[st.peek()] > a[i]) st.pop();
      if (st.isEmpty()) {
        lastSmallElement[i] = -1;
      } else {
        lastSmallElement[i] = st.peek();
      }
      st.push(i);
    }
    return lastSmallElement;
  }

  private static int[] getLargestRectangleForEachElement(int[] a) {
    int n = a.length;
    int[] nextSmallElements = findNextSmallElement(a);
    int[] lastSmallElements = findLastSmallElement(a);
    int[] rectangle = new int[n];
    for (int i = 0; i < n; i++) {
      int startIndex = lastSmallElements[i] + 1;
      int endIndex = nextSmallElements[i] - 1;
      int height = a[i];
      int width = endIndex - startIndex + 1;
      int area = height * width;
      rectangle[i] = area;
    }
    return rectangle;
  }

  private static void getLargestRectangleArea(int[] a) {
    int n = a.length;
    int[] nextSmallElements = findNextSmallElement(a);
    int[] lastSmallElements = findLastSmallElement(a);
    int[] rectangle = new int[n];
    int maxAreaIndex = 0;
    int maxArea = 0;
    for (int i = 0; i < n; i++) {
      int startIndex = lastSmallElements[i] + 1;
      int endIndex = nextSmallElements[i] - 1;
      int height = a[i];
      int width = endIndex - startIndex + 1;
      int area = height * width;
      rectangle[i] = area;
      if (maxArea < area) {
        maxAreaIndex = i;
        maxArea = area;
      }
    }
    System.out.println(
        "max area="
            + rectangle[maxAreaIndex]
            + " -->["
            + (lastSmallElements[maxAreaIndex] + 1)
            + ","
            + (nextSmallElements[maxAreaIndex] - 1)
            + "] for element at = " + maxAreaIndex);
  }



  public static void main(String[] args) {
    int[] temp = {2, 1, 5, 6, 2, 3}; // [1,4,4,6,4,5,6,7]

    System.out.println(Arrays.toString(findNextSmallElement(temp)));
    System.out.println(Arrays.toString(findLastSmallElement(temp)));
    System.out.println(Arrays.toString(getLargestRectangleForEachElement(temp)));
    getLargestRectangleArea(temp);
  }
}
