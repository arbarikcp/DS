package Maximal.Fundamental;

public class Diophantine_Equation {

  public static void main(String[] args) {

    Tuple solution = new Tuple(0, 0);
    //ax + by = 2 * 4 + 3 * -2 = 14

    solution = getAnySolution(3,3,14);
    if (solution != null) {
      System.out.println("solution " + solution.first + " " + solution.next);
    }
    // how to find all solutions for the equation

  }

  public static Tuple getAnySolution(int a, int b, int c){  // find x and y -> ax + by = c

    Tuple coefficient = new Tuple(0, 0);
    int gcd = GCD_coefficients.get_GCD(a, b, coefficient);
    if( c % gcd != 0){
      System.out.println("there is no solution");
      return null;
    }
    int x = coefficient.first * (c /gcd);
    int y = coefficient.next * (c/gcd);
    if(a < 0) x = -x;
    if(b < 0) y =-y;
    Tuple solution = new Tuple(x, y);
    return solution;

   }

}
