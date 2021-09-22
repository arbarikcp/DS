package SD.ConsistentHashing.KV;

import SD.ConsistentHashing.Router.ConsistentHashRouter;
import SD.ConsistentHashing.Router.SimpleConsistentHashRouter;
import SD.ConsistentHashing.Router.SimpleConsistentHashRouterWithVirtualNodes;
import SD.ConsistentHashing.Router.SimpleConsistentHashingWithReplication;
import SD.ConsistentHashing.Router.SimpleConsistentHashingWithReplicationAndVirtualNode;
import SD.Hash.FNV1_32_HashingAlgorithms;
import SD.Hash.HashingAlgorithm;
import java.util.List;


public class KVClient {

  List<String> serverIds;
  ConsistentHashRouter router;

  public KVClient(List<String> serverIds) {
    this.serverIds = serverIds;
    HashingAlgorithm hashingAlgorithms = new FNV1_32_HashingAlgorithms();
    //this.router = new SimpleConsistentHashRouter(serverIds, hashingAlgorithms);
    //this.router = new SimpleConsistentHashRouterWithVirtualNodes(serverIds, hashingAlgorithms,5);
    this.router = new SimpleConsistentHashingWithReplication(serverIds, hashingAlgorithms, 1);
    //this.router = new SimpleConsistentHashingWithReplicationAndVirtualNode(serverIds, hashingAlgorithms, 1,3);

  }

  public void putData(String key, String value) {
    router.putData(key, value);
  }

  public String getData(String key) {
    return router.getData(key);
  }

  public String removeData(String key){
    return router.removeData(key);
  }



  public void addNewServer(String serverId){
    router.addNewServer(serverId);
  }

  public void removeOneServer(String serverId){
    router.removeOneServer(serverId);
  }

  public void printStatus(){
    router.printStatus();
  }
}
