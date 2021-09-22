package Maximal.Fundamental;

import java.util.Arrays;

public class PrimeNumbers {

  public static void main(String[] args) {
    Sieve_of_Eratosthenes(1000);
  }

  public static void Sieve_of_Eratosthenes(int n){
    Boolean isPrime[] = new Boolean[n];
    Arrays.fill(isPrime, true);
    isPrime[0] = isPrime[1] = false;
    for(int i = 2; i * i < n; i++){
      if(isPrime[i]){
        for(int j = i * i; j < n; j += i){
          isPrime[j] = false;
        }
      }
    }

    int  i = 0;
    for(Boolean b : isPrime){
      if(b){
        System.out.print(i + ",");
      }
      i++;
    }
  }

}
