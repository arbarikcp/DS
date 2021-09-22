package MonotonicQueue;

import java.util.Arrays;
import java.util.Stack;

public class DailyTemperature {
  private int[] temperatures;
  private int[] nearest;
  private int[] result;

  public DailyTemperature(int temps[]){
    temperatures = temps;
    nearest = new int[temperatures.length];
    result = new int[temperatures.length];
  }

  public int[] getTempIncreasingResult()
  {
    int n = temperatures.length;

    for (int i = n - 2; i >= 0; i--)
    {
     int j = indexOfNextBigNumber(i);
      nearest[i] = j;
      result[i] = nearest[i] == n ? 0 : nearest[i] - i;
    }
    return result;
  }

  private int indexOfNextBigNumber(int i ){
    int j = i + 1;
    int n = temperatures.length;
    while (j < n && temperatures[j] <= temperatures[i]) // looking for a nearest biggest value
    {
      j = nearest[j];
    }
     return j == n ? i : j;
  }

  public static void main(String[] args) {
    int[] temp = {89,62,70,58,47,76,100};
    System.out.println(Arrays.toString(new DailyTemperature(temp).getTempIncreasingResult()));
  }


}
