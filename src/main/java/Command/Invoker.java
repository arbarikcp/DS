package Command;

import java.util.HashMap;
import java.util.Map;

public class Invoker {

  private Map<Integer,Command> commandList;

  public Invoker() {
    commandList = new HashMap<Integer, Command>();
  }

  public void addCommand( Integer key, Command command){
    commandList.put(key, command);
  }

  public  void execute(Integer key){
    Command command = commandList.get(key);
    if(command != null){
        command.execute();
    }else{
      System.out.println("wrong command");
    }
  }

  private void executeAll(){

  }

}
