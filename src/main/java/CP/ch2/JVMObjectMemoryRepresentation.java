package CP.ch2;

import org.openjdk.jol.info.ClassLayout;

public class JVMObjectMemoryRepresentation {

  public static void main(String[] args) {
    //https://www.baeldung.com/java-memory-layout
    //https://shipilev.net/jvm/objects-inside-out/


    boolean[] bits = new boolean[1024];
    System.out.println(ClassLayout.parseInstance(bits).toPrintable());

  }
}
