package SD.ConsistentHashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class SimpleConsistentHashing {

  Map<String, PhysicalServer> servers = new HashMap<>();
  private List<String> physicalNodeIds = new ArrayList<>();
  private SortedMap<Integer, String> physicalNodeRing = new TreeMap<>();

  public SimpleConsistentHashing(List<String> physicalNodeIds) {
    this.physicalNodeIds.addAll(physicalNodeIds);
    for (String serverId : physicalNodeIds) {
      int hash = getHash(serverId);
      System.out.println("[" + serverId + "]Join the collection, his Hash The value is " + hash);
      physicalNodeRing.put(hash, serverId); // place the servers on the hash ring.
      servers.put(serverId, new PhysicalServer(serverId));
    }
  }

  public void putData(String key, String value) {
    String serverId = getServerId(key);
    PhysicalServer server = servers.get(serverId);
    //System.out.println(key+ " will be routed to "+ serverId);
    server.putData(key, value);
  }

  public String getData(String key) {
    String serverId = getServerId(key);
    PhysicalServer server = servers.get(serverId);
    //System.out.println(key+ " get request will be routed to "+ serverId);
    return server.getData(key);
  }

  public String getServerId(String dataKey) {
    int hash = getHash(dataKey); // get the exact position of key on the ring.
    //Get the server which are positioned beyond the position of the key on the ring.
    //tailMap returns all the map entries whose keys are greater than the given hash of the incoming key.
    SortedMap<Integer, String> subMap = physicalNodeRing.tailMap(hash);
    if (subMap.isEmpty()) {
      // if this is empty, means there is no server beyond this point,
      // as it is a circular ring We need to go to the firstKey of the entier server list.
      //.firstKey() returns map entry with lowest key
      Integer firstServerHash = physicalNodeRing.firstKey();
      return physicalNodeRing.get(firstServerHash);
    } else {
      //if this is not empty, means there is  servers beyond this point,
      //Then put the data to the first server from the list.
      Integer nextServerHash = subMap.firstKey();
      return subMap.get(nextServerHash);
    }
  }

  public void addNewServer(String serverId) {
    System.out.println("Adding new server " + serverId);
    physicalNodeIds.add(serverId);
    servers.put(serverId, new PhysicalServer(serverId));
    //Find the position of new server on the ring
    Integer hash = getHash(serverId);
    physicalNodeRing.put(hash, serverId);
    //Find the next server position, there may be some keys which are being hold by next server,
    // but can be rerouted/rehashed to this new server.
    SortedMap<Integer, String> subMap = physicalNodeRing.tailMap(hash + 1);
    String nextServerId = null;
    if (subMap.isEmpty()) {
      Integer firstServerHash = physicalNodeRing.firstKey();
      nextServerId = physicalNodeRing.get(firstServerHash);
    } else {
      Integer nextServerHash = subMap.firstKey();
      nextServerId = subMap.get(nextServerHash);
    }
    reHashAllServerKey(nextServerId);
  }

  private void reHashAllServerKey(String serverId) {
    PhysicalServer holdingServer = servers.get(serverId);
    Map<String, String> allServerData = holdingServer.getAllDataFromServer();
    for (String key : allServerData.keySet()) {
      String newServerId = getServerId(key);
      if (!newServerId.equals(serverId)) {
        PhysicalServer server = servers.get(newServerId);
        //System.out.println(key + " will be routed to new " + newServerId);
        server.putData(key, allServerData.get(key));
        //delete the keys from existing holding server which has been rehashed to new server.
        holdingServer.removeData(key);
      }
    }

    System.out.println("Rehashed all the keys of node " + serverId);
  }

  //DataKey  and the serverId all need to be hash by using same hash method. As all need to be present on the same ring.

  public void printStatus() {
    for (Integer serverHash : physicalNodeRing.keySet()) {
      String serverId = physicalNodeRing.get(serverHash);
      PhysicalServer server = servers.get(serverId);
      System.out.print(
          "(" + serverHash + "-" + server.getId() + "-" + String.valueOf(server.getKeyCount())
              + ")->");
    }
    System.out.println();
  }


  //Using FNV1_32_HASH algorithm to calculate the Hash value of the server, there is no need to rewrite hashCode method, the final effect is no difference.
  private static int getHash(String str) {
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
