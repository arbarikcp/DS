package SD.ConsistentHashing.Router;

public class VirtualNode {

  private String id;
  private PhysicalNode mappedPhysicalNode;
  private int keyCount;

  public VirtualNode(String id, PhysicalNode mappedPhysicalNode) {
    this.id = id;
    this.mappedPhysicalNode = mappedPhysicalNode;
    this.mappedPhysicalNode.addVirtualNode(id);
    this.keyCount = 0;
  }

  public String getId(){return id;}

  public String getPhysicalNodeId(){
    return mappedPhysicalNode.getId();
  }

  public synchronized void incrKeyCount(){keyCount++;}
  public synchronized void decrKeyCount(){keyCount--;}

}
