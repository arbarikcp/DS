
package rishu.hrishit;

import java.io.IOException;
import java.util.*;



//State, City,Area , apartment name, wing, flat number

public class Test {


  public static void main(String[] args) throws IOException {

    // Input
    // Output
    // Error

    //System.in;
    //System.out
    //System.err
    // "55555555555555555555555555555555555"
    // "                "


    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.print("Enter a length of rectangle: ");
      int length = scanner.nextInt();

      System.out.print("Enter a width of rectangle: ");
      int width = scanner.nextInt();

      int area = area(width, length);
      System.out.println("area of rectangle = " + area);

      System.out.println("Press any key to exit");
      scanner.next();

      for(int clear = 0; clear < 100; clear++)
      {
        System.out.println("\b") ;
      }
    }
  }

  static int area(int width, int length){
    return width * length;
  }

}