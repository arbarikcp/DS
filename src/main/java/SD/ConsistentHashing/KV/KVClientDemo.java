package SD.ConsistentHashing.KV;

import SD.ConsistentHashing.Router.ConsistentHashRouter;
import SD.ConsistentHashing.Router.PhysicalNode;
import SD.ConsistentHashing.Router.SimpleConsistentHashRouter;
import SD.Hash.FNV1_32_HashingAlgorithms;
import SD.Hash.HashingAlgorithm;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class KVClientDemo {

  public static void main(String[] args) {

    String[] serverIds = {"192.168.0.0:111", "192.168.0.1:111",
        "192.168.0.2:111", "192.168.0.3:111", "192.168.0.4:111"};

    KVClient kvClient = new KVClient(Arrays.asList(serverIds));
    List<String> storedKeys = new ArrayList<>();
    //Add KV pair
    for (int i = 0; i < 10; i++) {
      String uniqueKey = generatingRandomAlphanumericString();
      String key = i+"-"+uniqueKey;
      kvClient.putData(key, key);
      storedKeys.add(key);
    }

    kvClient.printStatus();
    kvClient.addNewServer("192.138.0.2:111");
    kvClient.printStatus();
/*    kvClient.removeOneServer("192.168.0.0:111");
    kvClient.printStatus();*/

    // Remove few data
    System.out.print("Removing keys[");
    List<String> removedKey = new ArrayList<>();
    for(int i = 0; i < 3; i++){
      String key = storedKeys.get(new Random(10).nextInt(storedKeys.size()));
      kvClient.removeData(key);
      storedKeys.remove(key);
      System.out.print(key+",");
    }
    System.out.println("]");

    kvClient.printStatus();

/*    kvClient.addNewServer("128.138.0.2:111");
    kvClient.printStatus();
    kvClient.addNewServer("134.131.0.1:111");
    kvClient.printStatus();
    kvClient.addNewServer("191.108.0.4:111");
    kvClient.printStatus();
    kvClient.removeOneServer("192.168.0.0:111");
    kvClient.printStatus();*/


    //Get All KV pair
    for(String key: storedKeys){
      assert kvClient.getData(key) == key;
    }
    //assert kvClient.getData("google") == "google";

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
