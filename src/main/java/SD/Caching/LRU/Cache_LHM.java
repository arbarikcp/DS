package SD.Caching.LRU;

import java.util.LinkedHashMap;
import java.util.Map;


public class Cache_LHM<K,V> extends LinkedHashMap<K,V> {

  Cache_LHM(){
    super(4,0.75f,true);
  }

  @Override
  protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
    return size() > 4;
  }

  public static void main(String[] args) {
    Cache_LHM<Integer, Integer> cache = new Cache_LHM();
    cache.put(1,1);
    cache.put(2,2);
    cache.put(3,3);
    cache.get(1);
    cache.get(2);
    cache.put(4,4);
    cache.put(5,5);

    System.out.println(cache);
  }
}
