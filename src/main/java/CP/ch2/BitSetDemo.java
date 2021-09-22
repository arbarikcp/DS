package CP.ch2;

import java.util.BitSet;
import org.openjdk.jol.info.GraphLayout;

public class BitSetDemo {

  public static void main(String[] args) {

    BitSet bitSet = new BitSet(1024);
    //System.out.println(GraphLayout.parseInstance(bitSet).toPrintable());

    bitSet.set(23);
    System.out.println(bitSet.get(23));
    System.out.println(bitSet.get(24));
    bitSet.clear(23);
    System.out.println(bitSet.get(23));

    bitSet.set(10,30, true);

    bitSet.stream().forEach(System.out::println);

  }
}
