package Command;

public class SimpleCommand implements Command {

  @Override
  public void execute() {
    System.out.println("Executing simple command 1");
  }
}

class SimpleCommand2 implements Command {

  @Override
  public void execute() {
    System.out.println("Executing simple command 2");
  }
}
class SimpleCommand3 implements Command {

  @Override
  public void execute() {
    System.out.println("Executing simple command 3");
  }
}
class SimpleCommand4 implements Command {

  @Override
  public void execute() {
    System.out.println("Executing simple command 4");
  }
}