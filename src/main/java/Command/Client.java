package Command;

public class Client {

  public static void main(String[] args) {
    Invoker invoker = new Invoker();
    SimpleCommand2 command2 = new SimpleCommand2();
    SimpleCommand3 command3 = new SimpleCommand3();

    invoker.addCommand(2,command2);
    invoker.addCommand(3, command3);

    invoker.execute(3);
    invoker.execute(2);
    invoker.execute(1);

  }
}
