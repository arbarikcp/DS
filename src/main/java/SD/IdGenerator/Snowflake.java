package SD.IdGenerator;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Enumeration;

public class Snowflake {

  private static final int TIME_BITS=41,NODE_BITS = 10, SEQ_BITS=12;
  int MAX_SEQ = 4098;
  private final long EPOCH_START = Instant.EPOCH.toEpochMilli();
  long lastTimestamp;
  int nodeId, sequence = 0;

  public Snowflake() {
    this.nodeId = generatingNodeId();
    lastTimestamp = System.currentTimeMillis();
  }

  public Long generateId() throws Exception {
  long currentTimestamp = System.currentTimeMillis();
  if(lastTimestamp > currentTimestamp){
    throw new Exception("NTP skew happened, clock moved backward");
  }
  if(lastTimestamp == currentTimestamp){
    if(sequence + 1 > MAX_SEQ){
        waitNextMillis(currentTimestamp);
        sequence = 0;
    }
  } else { //Next millisecond started
    sequence = 0;
  }
  lastTimestamp = currentTimestamp;
  long id = currentTimestamp << ( NODE_BITS + SEQ_BITS );
  id |= (nodeId << SEQ_BITS);
  id |= sequence++;
  return id;
  }

  private long getTimeStamp() {
    return Instant.now().toEpochMilli() - EPOCH_START;
  }

  private long waitNextMillis(long currentTimeStamp) {
    while (currentTimeStamp == lastTimestamp) {
      currentTimeStamp = getTimeStamp();
    }
    return currentTimeStamp;
  }

  private Integer generatingNodeId() {
    int maxNodeVal = (int) Math.pow(2, NODE_BITS);
    int nodeId;
    try {
      StringBuilder sb = new StringBuilder();
      Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
      while (networkInterfaces.hasMoreElements()) {
        NetworkInterface networkInterface = networkInterfaces.nextElement();
        byte[] mac = networkInterface.getHardwareAddress();
        if (mac != null) {
          for (int i = 0; i < mac.length; i++) {
            sb.append(String.format("%02X", mac[i]));
          }
        }
      }
      nodeId = sb.toString().hashCode();
    } catch (SocketException ex) {
      //in case of exception get a random number limited by max node size
      nodeId = (int) (new SecureRandom().nextInt() % Math.pow(2, 10));
    }
    nodeId = nodeId & maxNodeVal;
    return nodeId;
  }

  public static void main(String[] args) throws Exception {
    Snowflake idGenerator = new Snowflake();

    for(int i = 0; i < 5000; i++){
      System.out.println(idGenerator.generateId());
    }
  }
}
