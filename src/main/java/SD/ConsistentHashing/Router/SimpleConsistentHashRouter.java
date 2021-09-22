package SD.ConsistentHashing.Router;

import SD.Hash.HashingAlgorithm;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;

// DataKey  and the serverId all need to be hash by using same hash method. As all need to be
// present on the same ring.

public class SimpleConsistentHashRouter extends AbstractConsistentHashRouter {

  public SimpleConsistentHashRouter(List<String> physicalNodeIds, HashingAlgorithm hashStrategy) {
    super(physicalNodeIds, hashStrategy);
    addPhysicalServersOnTheRing();
  }

  private void addPhysicalServersOnTheRing() {
    for (String serverId : servers.keySet()) {
      int hash = hashStrategy.getHash(serverId);
      System.out.println("[" + serverId + "] Join the collection, its Hash value is " + hash);
      nodeRing.put(hash, serverId); // place the servers on the hash ring.
    }
  }

  @Override
  public void addNewServer(String serverId) {
    System.out.println("Adding new server " + serverId);
    servers.put(serverId, new PhysicalNode(serverId));
    // Find the position of new server on the ring
    Integer hash = hashStrategy.getHash(serverId);
    nodeRing.put(hash, serverId);
    // Find the next server position, there may be some keys which are being hold by next server,
    // but can be rerouted/rehashed to this new server.
    String nextServerId = getNextServerIdFromRing(serverId);
    reHashAllKeysForServer(nextServerId);
  }

  @Override
  public List<String> getServerId(String dataKey) {
    List<String> serverIdList = new ArrayList<>();
    int hash = hashStrategy.getHash(dataKey); // get the exact position of key on the ring.
    // Get the server which are positioned beyond the position of the key on the ring.
    // tailMap returns all the map entries whose keys are greater than the given hash of the
    // incoming key.
    SortedMap<Integer, String> subMap = nodeRing.tailMap(hash);
    if (subMap.isEmpty()) {
      // if this is empty, means there is no server beyond this point,
      // as it is a circular ring We need to go to the firstKey of the entier server list.
      // .firstKey() returns map entry with lowest key
      Integer firstServerHash = nodeRing.firstKey();
      serverIdList.add(nodeRing.get(firstServerHash));
    } else {
      // if this is not empty, means there is  servers beyond this point,
      // Then put the data to the first server from the list.
      Integer nextServerHash = subMap.firstKey();
      serverIdList.add(subMap.get(nextServerHash));
    }
    return serverIdList;
  }
}
