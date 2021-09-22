package SD.ConsistentHashing.Router;

// DataKey  and the serverId all need to be hash by using same hash method. As all need to be
// present on the same ring.

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public interface ConsistentHashRouter {

  Map<String, PhysicalNode> servers = new HashMap<>(); // serverId hash to server map

  // node ring, hash of servers( physical, virtual) to serverId
  SortedMap<Integer, String> nodeRing = new TreeMap<>();


  void putData(String key, String value);

  String getData(String key);

  String removeData(String key);

  void addNewServer(String serverId);

  void removeOneServer(String serverId);

  List<String> getServerId(String dataKey);

  String getNextServerIdFromRing(String serverId);

  String getPreviousServerIdFromRing(String serverId);

  PhysicalNode getPhysicalServer(String serverIdOnNodeRing);

  List<String> getNextServerIdsFromRing(String serverId, int count);

  List<String> getPreviousServerIdsFromRing(String serverId, int count);

  void printStatus();
}
