package SD.ConsistentHashing;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PhysicalServer {

  private String id;
  private ConcurrentHashMap<String,String> data;  // the data may be simultaneously added and deleted.

  public PhysicalServer(String id) {
    this.id = id;
    data = new ConcurrentHashMap<>();
  }

  public String getId() {
    return id;
  }

  public  String getData(String key) {
    String keyData = data.get(key);
    if(keyData == null){
      System.out.println("cache miss");
    }
    return  keyData;
  }

  public synchronized void  putData(String key, String value){
    data.put(key, value);
    //System.out.println("Added key "+ key + " to server "+ id);
  }

  public Map<String,String> getAllDataFromServer(){
    return Collections.unmodifiableMap(data);
  }

  public int getKeyCount(){
    return data.size();
  }

  public synchronized void removeData(String key){
    data.remove(key);
    //System.out.println("Removed key "+ key + " from server "+ id);
  }

}
