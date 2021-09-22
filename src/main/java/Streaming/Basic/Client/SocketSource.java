package Streaming.Basic.Client;

import Streaming.Basic.Api.Model.Event;
import Streaming.Basic.Api.Model.Source;
import java.util.ArrayList;
import java.util.List;


public class SocketSource extends Source {

  private SocketReader socketReader;
  public SocketSource(String name, int port) {
    super(name);
    this.socketReader = new SocketReader(port);
  }

  @Override
  public List<Event> getEvents(){
    List<Event> events = new ArrayList<>();
    try{
      String data = socketReader.getNextString();
      events.add(new Event(data));
      return events;
    } catch (Exception e){

    }
    return events;
  }

}
