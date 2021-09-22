package SD.ConsistentHashing.Router;

import SD.Hash.HashingAlgorithm;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

public class SimpleConsistentHashRouterWithVirtualNodes extends  AbstractConsistentHashRouter {

  private int virtualNodesPerServer;
  private Map<String, VirtualNode> virtualNodes = new HashMap<>();

  public SimpleConsistentHashRouterWithVirtualNodes(List<String> physicalNodeIds,
      HashingAlgorithm hashStrategy,int virtualNodesPerServer) {
    super(physicalNodeIds, hashStrategy);
    this.virtualNodesPerServer = virtualNodesPerServer;
    addVirtualServersToRing();
  }

  @Override
  public List<String> getServerId(String key) {
    List<String> serverIdList = new ArrayList<>();
    String virtualServerId = getVirtualServerId(key);
    VirtualNode virtualNode = virtualNodes.get(virtualServerId);
    serverIdList.add(virtualNode.getPhysicalNodeId());
    return serverIdList;
  }

  @Override
  public PhysicalNode getPhysicalServer(String serverIdOnNodeRing) {
    VirtualNode virtualNode = virtualNodes.get(serverIdOnNodeRing);
    if(virtualNode != null){
      String physicalNodeId = virtualNode.getPhysicalNodeId();
      return servers.get(physicalNodeId);
    }else{
      return null;
    }
  }


  @Override
  public void addNewServer(String serverId) {
    System.out.println("Adding new server " + serverId);
    PhysicalNode physicalNode = new PhysicalNode(serverId);
    servers.put(serverId, physicalNode);

    //Add virtual server to the Ring
    List<String> virtualServersToAdd = addVirtualServersToRingForSingleServer(serverId);

    for(String virtualServerId: virtualServersToAdd){
      reHashAllKeysForVirtualServer(virtualServerId);
    }
  }

  private void reHashAllKeysForVirtualServer(String virtualServerId){
    String nextServerId = getNextServerIdFromRing(virtualServerId);
    String physicalServerId = virtualNodes.get(nextServerId).getPhysicalNodeId();
    reHashAllKeysForServer(physicalServerId);
  }

  private String getVirtualServerId(String key) {
    int hash = hashStrategy.getHash(key);
    SortedMap<Integer, String> subMap = nodeRing.tailMap(hash);
    if (subMap.isEmpty()) {
      Integer firstServerHash = nodeRing.firstKey();
      return nodeRing.get(firstServerHash);
    } else {
      Integer nextServerHash = subMap.firstKey();
      return subMap.get(nextServerHash);
    }
  }

  private void addVirtualServersToRing(){
    for(String serverId: servers.keySet()){
      addVirtualServersToRingForSingleServer(serverId);
    }
  }

  private  List<String> addVirtualServersToRingForSingleServer(String serverId){
    PhysicalNode physicalNode = servers.get(serverId);
    List<String> virtualIds = new ArrayList<>();
    for(int i = 0; i < virtualNodesPerServer; i++){
      // Find the position of new virtual server on the ring
      String virtualServerId = generateVirtualServerId(serverId,i);
      virtualNodes.put(virtualServerId, new VirtualNode(virtualServerId, physicalNode));
      Integer hash = hashStrategy.getHash(virtualServerId);
      nodeRing.put(hash, virtualServerId);
      virtualIds.add(virtualServerId);
    }
    return virtualIds;
  }

  @Override
  public void printStatus() {
    int keyCount = 0;
    System.out.println("----------------------------------------------------------------------");
    System.out.format("%10s%7s%2s%2s%2s", "Server Id","|","Key count","|","Keys" );
    System.out.println();
    System.out.println("----------------------------------------------------------------------");
    for(String serverId : servers.keySet()){
      PhysicalNode server = servers.get(serverId);
      System.out.format("%10s%2s%5d%6s%5s", server.getId(),"|",server.getKeyCount(),"|",server.getAllData().keySet() );
      System.out.println();
      keyCount = keyCount + server.getKeyCount();
    }
    System.out.println("----------------------Total Key count = " + keyCount+" -----------------------------");
    printNodeRing();
  }
}
