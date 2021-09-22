package Maximal.Fundamental;

public class BinaryExponentiation {

  public static void main(String[] args) {
    System.out.println(bin_pow(3,5));
    System.out.println(bin_pow_log_approach(3,5));
    System.out.println(modNofPow(2,7,3));

  }

  public static long bin_pow(int a, int b){

    if(b == 0) return 1;
    long half = bin_pow(a, b/2);
    if(b % 2 == 0){ return half * half;}
    else { return a * half * half; }
  }

public static long bin_pow_log_approach(int a, int b){
    long res = 1;
    long currentLogPowerOfa = a;
    while (b > 0){
      if( (b & 1) == 1){
        res = res * currentLogPowerOfa;
      }
      b = b >> 1;
      currentLogPowerOfa = currentLogPowerOfa * currentLogPowerOfa;
    }
    return res;
}

  public static long modNofPow(int a, int b, int n) {
    long res = 1;
    long currentModOfaPower = a % n;
    while (b > 0) {
      if ((b & 1) == 1) {
        res = (res * currentModOfaPower) % n;
      }
      currentModOfaPower = (currentModOfaPower * currentModOfaPower) % n;
      b = b >> 1;
    }
    return res;
  }
}
