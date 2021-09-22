package SD.ConsistentHashing.Router;

import SD.Hash.HashingAlgorithm;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

public class SimpleConsistentHashingWithReplication extends SimpleConsistentHashRouter {

  int replicationFactor;

  public SimpleConsistentHashingWithReplication(
      List<String> physicalNodeIds, HashingAlgorithm hashStrategy, int replicationFactor) {
    super(physicalNodeIds, hashStrategy);
    this.replicationFactor = replicationFactor;
    if (replicationFactor >= servers.size()) {
      this.replicationFactor = servers.size() / 2;
    }
  }

  @Override
  public List<String> getServerId(String dataKey) {
    List<String> serverIdList = new ArrayList<>();
    String mainServerId;
    int hash = hashStrategy.getHash(dataKey);
    SortedMap<Integer, String> subMap = nodeRing.tailMap(hash);
    if (subMap.isEmpty()) {
      Integer firstServerHash = nodeRing.firstKey();
      mainServerId = nodeRing.get(firstServerHash);
    } else {
      Integer nextServerHash = subMap.firstKey();
      mainServerId = subMap.get(nextServerHash);
    }

    serverIdList.add(mainServerId);
    String serverId = mainServerId;
    for (int i = 1; i <= replicationFactor; i++) {
      String nextServerId = getNextServerIdFromRing(serverId);
      serverIdList.add(nextServerId);
      serverId = nextServerId;
    }
    return serverIdList;
  }

  @Override
  public void addNewServer(String serverId) {
    System.out.println("Adding new server " + serverId);
    servers.put(serverId, new PhysicalNode(serverId));
    // Find the position of new server on the ring
    Integer hash = hashStrategy.getHash(serverId);
    nodeRing.put(hash, serverId);

    // Rehash all the keys in forward servers (We need to rehash r+1 forward servers)
    for(String serverIdToRehash : getNextServerIdsFromRing(serverId, replicationFactor + 1)){
      reHashAllKeysForServer(serverIdToRehash);
    }

    for(String serverIdToRehash : getPreviousServerIdsFromRing(serverId, replicationFactor)){
      reHashAllKeysForServer(serverIdToRehash);
    }
  }

  protected void reHashAllKeysForServer(String serverId) {
    PhysicalNode holdingServer = servers.get(serverId);
    Map<String, String> allServerData = holdingServer.getAllData();
    for (String key : allServerData.keySet()) {
      String value = allServerData.get(key);
      List<String> newServerIds = getServerId(key);
      holdingServer.removeData(key);
      for (String newServerId : newServerIds) {
        PhysicalNode server = servers.get(newServerId);
        server.putData(key, value);
      }
    }
  }

  @Override
  public void removeOneServer(String serverId) {

    Integer serverIdHash = hashStrategy.getHash(serverId);
    List<String> nextServerIds = getNextServerIdsFromRing(serverId, replicationFactor);
    List<String> backwardServerIds =  getPreviousServerIdsFromRing(serverId, replicationFactor);

    nodeRing.remove(serverIdHash);
    servers.remove(serverId);

    for(String serverIdToRehash : nextServerIds){
      reHashAllKeysForServer(serverIdToRehash);
    }

    for(String serverIdToRehash : backwardServerIds){
      reHashAllKeysForServer(serverIdToRehash);
    }
  }

  @Override
  public String removeData(String key){
    String val = null;
    List<String> serverIds = getServerId(key);
    for (String serverId : serverIds) {
      PhysicalNode server = servers.get(serverId);
      val = server.removeData(key);
    }
    return val;
  }

}
