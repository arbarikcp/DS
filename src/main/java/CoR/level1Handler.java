package CoR;

public class level1Handler extends AbstractHandler {

  public level1Handler(Handler next) {
    nextHandler = next;
  }

  @Override
  public void handle() {
    System.out.println("Handling request at level 1");
    next();
  }
}

class level2Handler extends AbstractHandler {

  public level2Handler(Handler next) {
    nextHandler = next;
  }

  @Override
  public void handle() {
    System.out.println("Handling request at level 2");
    next();
  }
}

class level3Handler extends AbstractHandler {

  public level3Handler(Handler next) {
    nextHandler = next;
  }

  @Override
  public void handle() {
    System.out.println("Handling request at level 3");
    next();
  }
}