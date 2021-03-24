package SD.ConsistentHashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ConsistentHashingClient {

  public static void main(String[] args) {

    String[] serverIds = {"192.168.0.0:111", "192.168.0.1:111",
        "192.168.0.2:111", "192.168.0.3:111", "192.168.0.4:111"};

    Map<String, PhysicalServer> servers = new HashMap<>();
    for (String serverId : serverIds) {
      servers.put(serverId, new PhysicalServer(serverId));
    }

    SimpleConsistentHashing sch = new SimpleConsistentHashing(Arrays.asList(serverIds));

    String[] keys = {"sunlight", "Moon", "Stars", "Moon1", "Stars1", "Moon2", "Moon3"};
    List<String> keyList = new ArrayList<>();
    keyList.addAll(Arrays.asList(keys));
    for (String key : keyList) {
      sch.putData(key, key);
    }

    for (int i = 0; i < 1000; i++) {
      String key = generatingRandomAlphanumericString();
      sch.putData(key, key);
    }

    sch.printStatus();
    sch.addNewServer("192.138.0.2:111");
    sch.printStatus();

    sch.addNewServer("128.138.0.2:111");
    sch.printStatus();
    sch.addNewServer("134.131.0.1:111");
    sch.printStatus();
    sch.addNewServer("191.108.0.4:111");
    sch.printStatus();
  }


  public static String generatingRandomAlphanumericString() {
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
