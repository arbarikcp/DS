package CoR;

public class client {

  public static void main(String[] args) {
    level1Handler handler = new level1Handler(new level2Handler(new level3Handler(null)));
    handler.handle();

  }
}
