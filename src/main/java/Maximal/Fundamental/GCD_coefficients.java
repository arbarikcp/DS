package Maximal.Fundamental;

public class GCD_coefficients {

  public static void main(String[] args) {
    Tuple coefficient = new Tuple(0,0);
    int gcd = get_GCD(55, 80,coefficient);
    System.out.println("gcd=" + gcd +"  "+coefficient.first +" "+ coefficient.next);
  }

  public static int get_GCD(int a, int b , Tuple coefficient){
    if(b == 0){
      System.out.println("gcd ="+ a);
      coefficient.first = 1;
      coefficient.next = 0;
      return a;
    }
    Tuple t1 = new Tuple(0,0);
    int res = get_GCD(b, a % b, t1);
    coefficient.first = t1.next;
    coefficient.next = t1.first - t1.next * (a/b);
    return res;
  }
}


