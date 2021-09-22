package Maximal.Fundamental;

public class GCD {

  public static void main(String[] args) {

    System.out.println(LCM(18,27));
    System.out.println(calculate_gcd(55, 80));
    System.out.println(calculate_gcd_1(18, 27));
    System.out.println(calculate_gcd_2(18, 27));
  }

  public static int calculate_gcd(int a, int b){
    if( b == 0) {
      return a;
    }
    return calculate_gcd(b, a % b);
  }

  public static int calculate_gcd_1(int a, int b){
    return b != 0 ? calculate_gcd_1(b, a % b) : a;
  }

  public static int calculate_gcd_2(int a, int b){
    while ( b != 0){
      int c = a % b;
      a = b;
      b = c;
    }
    return a;
  }

  public static int LCM(int a, int b){
    return (a/calculate_gcd_1(a,b)) * b;
  }

}
