package DP;

import com.google.common.base.Joiner;
import java.util.HashMap;
import java.util.Map;

public class GridTraveller {
  // we have an m * n grid, we are top (m,n)
  // we can only move down or right.
  // How many move needed to reach at (1,1)

  Map<String, Long> memo = new HashMap<>();

  public static void main(String[] args) {
    System.out.println(new GridTraveller().getMoveCount(18, 18));
    assert new GridTraveller().getMoveCount(2, 3) == 3;
    assert new GridTraveller().getMoveCount(3, 3) == 6;
    assert new GridTraveller().getMoveCount(3, 2) == 3;
  }

  private String getKey(long r, long c) {
    return Joiner.on(":").join(Long.toString(r), Long.toString(c));
  }

  long getMoveCount(int r, int c) {

    // Memo check
    String key = getKey(r, c);
    if (memo.containsKey(key)) {
      return memo.get(key);
    }
    // base condition
    if (r == 1 && c == 1) {
      return 1; // base condition is the solution for smallest sub problem.
    }
    if (r < 1 || c < 1) {
      return 0;
    }

    // move
    long result = getMoveCount(r - 1, c) + getMoveCount(r, c - 1);
    // memo save
    memo.put(key, result);
    return result;
  }
}
