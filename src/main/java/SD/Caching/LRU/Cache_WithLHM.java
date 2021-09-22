package SD.Caching.LRU;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class Cache_WithLHM {

    static LinkedHashMap<String, Integer> lhm = new LinkedHashMap<String, Integer>(4,0.75f, true){
      @Override
      protected boolean removeEldestEntry(Entry<String, Integer> eldest) {
        return size() > 4 ;
      }
    };

  public static void main(String[] args) {
    lhm.put("a",1);
    lhm.put("b",2);
    lhm.put("c",3);
    lhm.get("a");
    lhm.get("b");
    lhm.put("d",4);
    lhm.put("e",5);

    System.out.println(lhm);
  }
}
