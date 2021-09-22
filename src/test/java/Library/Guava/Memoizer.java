package Library.Guava;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Memoizer {

  private static BigInteger generateBigNumber() {
    try {
      TimeUnit.SECONDS.sleep(2);
    } catch (InterruptedException e) {}
    return new BigInteger("12345");
  }

  private static Supplier<BigInteger> memoizedSupplier = Suppliers.memoize(Memoizer::generateBigNumber);

  private static Supplier<BigInteger> memoizedSupplierWithEviction =
      Suppliers.memoizeWithExpiration(Memoizer::generateBigNumber, 5, TimeUnit.SECONDS);

  private static void memoizerTest(){
    System.out.println(memoizedSupplier.get());
    System.out.println(memoizedSupplier.get());
    System.out.println(memoizedSupplier.get());
  }


  private static BigInteger fib(int n){
    System.out.println("--> fib("+n+")");
    if( n == 0) return BigInteger.ZERO;
    if (n == 1) return BigInteger.ONE;
    return fibMemo.getUnchecked(n-1).add(fibMemo.getUnchecked(n-2));
  }

  private static CacheLoader<Integer,BigInteger> cacheLoader = CacheLoader.from(Memoizer::fib);
  private static LoadingCache<Integer, BigInteger> fibMemo = CacheBuilder.newBuilder()
      .maximumSize(100)
      .build(cacheLoader);

  private  static BigInteger getFib(int n) {
    return fibMemo.getUnchecked(n);
  }


  private static void getFibTest(){
    System.out.println(getFib(4));
    System.out.println(getFib(8));
    System.out.println(getFib(10));
  }


  private static BigInteger factorial(int n){

    System.out.println("---> factorial("+n+")");
    if( n == 0) return BigInteger.ONE;
    if(n == 1) return BigInteger.ONE;
    return factorialCache.getUnchecked(n-1).multiply(BigInteger.valueOf(n));

  }

  private static CacheLoader<Integer, BigInteger> factorialCacheLoader = CacheLoader.from(Memoizer::factorial);
  private static LoadingCache<Integer,BigInteger> factorialCache = CacheBuilder.newBuilder()
      .maximumSize(100)
      .build(factorialCacheLoader);

  private static BigInteger getFactorial(int n){
    return factorialCache.getUnchecked(n);
  }


  private static void getFactorialTest(){
    System.out.println(getFactorial(10));
    System.out.println(getFactorial(20));
    System.out.println(getFactorial(30));
    System.out.println(getFactorial(15));
    System.out.println(getFactorial(17));
  }


  public static void main(String[] args) throws ExecutionException {
    // memoizerTest();
    // getFibTest();
    getFactorialTest();

  }
}
