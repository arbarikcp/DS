package SD.ConsistentHashing.Router;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PhysicalNode {

  private String id;
  private List<String> virtualNodeIds;
  private ConcurrentHashMap<String,String> data;  // the data may be simultaneously added and deleted.


  public PhysicalNode(String id) {
    this.id = id;
    virtualNodeIds = new ArrayList<>();
    data = new ConcurrentHashMap<>();
  }

  public String getId() {
    return id;
  }

  public  String getData(String key) {
    String keyData = data.get(key);
    if(keyData == null){
      //System.out.println("cache miss");
    }
    return  keyData;
  }

  public synchronized void  putData(String key, String value){
    data.put(key, value);
    //System.out.println("Added key "+ key + " to server "+ id);
  }
  public synchronized String removeData(String key){
    String value =  getData(key);
    if (value != null) {
      data.remove(key);
    }
    return value;
    //System.out.println("Removed key "+ key + " from server "+ id);
  }

  public Map<String,String> getAllData(){
    Map<String,String> allDataFromServer = new HashMap<>();
    allDataFromServer.putAll(data);
    return allDataFromServer;
  }

  public void addVirtualNode(String virtualNodeId){
    virtualNodeIds.add(virtualNodeId);
  }

  public List<String> getVirtualNodeIds(){return  virtualNodeIds;}

  public int getKeyCount(){
    return data.size();
  }

  @Override
  public String toString(){
    return id + "["+ virtualNodeIds +"]" + "["+ getKeyCount()+"]";
  }

}
