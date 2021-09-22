package SD.ConsistentHashing.Router;

import SD.Hash.HashingAlgorithm;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;

public class AbstractConsistentHashRouter implements ConsistentHashRouter {

  HashingAlgorithm hashStrategy;

  public AbstractConsistentHashRouter(List<String> physicalNodeIds, HashingAlgorithm hashStrategy) {
    this.hashStrategy = hashStrategy;
    for (String nodeId : physicalNodeIds) {
      servers.put(nodeId, new PhysicalNode(nodeId));
    }
  }

  @Override
  public void putData(String key, String value) {
    List<String> serverIds = getServerId(key);
    for (String serverId : serverIds) {
      PhysicalNode server = servers.get(serverId);
      server.putData(key, value);
    }
  }

  @Override
  public String getData(String key) {
    List<String> serverIds = getServerId(key);
    String val = null;
    for (String serverId : serverIds) {
      PhysicalNode server = servers.get(serverId);
      val = server.getData(key);
      if(val != null){
        break;
      }
    }
    return val;
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

  @Override
  public void removeOneServer(String serverId) {
    Integer serverIdHash = hashStrategy.getHash(serverId);
    nodeRing.remove(serverIdHash);
    servers.remove(serverId);
  }

  @Override
  public List<String> getServerId(String dataKey) { return null; }

  @Override
  public void addNewServer(String serverId) {
    System.out.println("Adding new server " + serverId);
    servers.put(serverId, new PhysicalNode(serverId));
  }

  @Override
  public String getNextServerIdFromRing(String serverId){
    Integer hash = hashStrategy.getHash(serverId);
    SortedMap<Integer, String> subMap = nodeRing.tailMap(hash + 1);
    String nextServerId = null;
    if (subMap.isEmpty()) {
      Integer firstServerHash = nodeRing.firstKey();
      nextServerId = nodeRing.get(firstServerHash);
    } else {
      Integer nextServerHash = subMap.firstKey();
      nextServerId = subMap.get(nextServerHash);
    }
    return nextServerId;
  }

  @Override
  public String getPreviousServerIdFromRing(String serverId) {
    Integer hash = hashStrategy.getHash(serverId);
    SortedMap<Integer, String> subMap = nodeRing.headMap(hash - 1);
    String previousServerId = null;
    if (subMap.isEmpty()) {
      Integer lastServerHash = nodeRing.lastKey();
      previousServerId = nodeRing.get(lastServerHash);
    } else {
      Integer previousServerHash = subMap.lastKey();
      previousServerId = subMap.get(previousServerHash);
    }
    return previousServerId;
  }

  @Override
  public PhysicalNode getPhysicalServer(String serverIdOnNodeRing) {
    return servers.get(serverIdOnNodeRing);
  }

  @Override
  public  List<String> getNextServerIdsFromRing(String serverId, int count){
    String currentId = serverId;
    List<String> serverIds = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      String nextServerId = getNextServerIdFromRing(currentId);
      serverIds.add(nextServerId);
      currentId = nextServerId;
    }
    return serverIds;
  }

  @Override
  public List<String> getPreviousServerIdsFromRing(String serverId, int count ){
    String currentId = serverId;
    List<String> serverIds = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      String previousServerId = getPreviousServerIdFromRing(currentId);
      serverIds.add(previousServerId);
      currentId = previousServerId;
    }
    return serverIds;
  }

  public String generateVirtualServerId(String serverId, int index) {
    String virtualServerId =
        serverId + ":" + index + ":" + generatingRandomAlphanumericString() + ":" + "VirtualNode";
    return virtualServerId;
  }

  protected void reHashAllKeysForServer(String serverId) {
    PhysicalNode holdingServer = servers.get(serverId);
    Map<String, String> allServerData = holdingServer.getAllData();
    for (String key : allServerData.keySet()) {
      String value = allServerData.get(key);
      System.out.print("...Adding key["+ key+"] and value["+ value+"]");
      List<String> newServerIds = getServerId(key);
      for (String newServerId : newServerIds) {
        if (!newServerId.equals(serverId)) {
          PhysicalNode server = servers.get(newServerId);
          // System.out.println(key + " will be routed to new " + newServerId);
          server.putData(key, value);
          System.out.println(" to server server["+ newServerId+"] removing from old server["+ serverId+"]");
          // delete the keys from existing holding server which has been rehashed to new server.
          holdingServer.removeData(key);
        }
      }
    }

    System.out.println("Rehashed all the keys of node " + serverId);
  }

  @Override
   public void printStatus() {
    int keyCount = 0;
    System.out.println("----------------------------------------------------------------------");
    System.out.format("%10s%7s%2s%2s%2s", "Server Id","|","Key count","|","Keys" );
    System.out.println();
    System.out.println("----------------------------------------------------------------------");
    for(Integer serverHashKey : nodeRing.keySet()){
      String serverId = nodeRing.get(serverHashKey);
      PhysicalNode server = servers.get(serverId);
      System.out.format("%10s%2s%5d%6s%5s", server.getId(),"|",server.getKeyCount(),"|",server.getAllData().keySet() );
      System.out.println();
      keyCount = keyCount + server.getKeyCount();
    }
    System.out.println("----------------------Total Key count = " + keyCount+" -----------------------------");
    printNodeRing();
  }

  protected void printNodeRing(){
    System.out.println("---------------------Node Ring------------");
    System.out.format("%10s%7s%2s", "Hash Id","|","Server Id" );
    System.out.println();
    System.out.println("------------------------------------------");
    System.out.println();
    for(Integer serverHashKey : nodeRing.keySet()){
      System.out.format( "%10s%7s%2s", serverHashKey,"|",nodeRing.get(serverHashKey));
      System.out.println();
    }
    System.out.println("------------------------------------------");
  }

  private static String generatingRandomAlphanumericString() {
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 10;
    Random random = new Random();

    String generatedString = random.ints(leftLimit, rightLimit + 1)
        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();

    return generatedString;
  }
}
