package SD.Hash;

public class FNV1_32_HashingAlgorithms implements HashingAlgorithm {

  //Using FNV1_32_HASH algorithm to calculate the Hash value of the server,
  //there is no need to rewrite hashCode method, the final effect is no difference.
  public int getHash(String str) {
    final int p = 16777619;
    int hash = (int) 2166136261L;
    for (int i = 0; i < str.length(); i++) {
      hash = (hash ^ str.charAt(i)) * p;
    }
    hash += hash << 13;
    hash ^= hash >> 7;
    hash += hash << 3;
    hash ^= hash >> 17;
    hash += hash << 5;

    // If the calculated value is negative, take its absolute value.
    if (hash < 0) {
      hash = Math.abs(hash);
    }
    return hash;
  }

}
