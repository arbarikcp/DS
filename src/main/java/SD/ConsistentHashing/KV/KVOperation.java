package SD.ConsistentHashing.KV;

public interface KVOperation {

  public void putData(String key, String value);

  public String getData(String key);

  public String removeData(String key);

}
