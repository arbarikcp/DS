package Streaming.Basic.Engine.Executor;


import Streaming.Basic.Api.Model.Event;
import Streaming.Basic.Api.Model.Source;
import Streaming.Basic.Engine.Pipes.EventQueue;
import java.util.ArrayList;
import java.util.List;

public class SourceExecutor extends ComponentExecutor {

  private final Source source;

  public SourceExecutor(Source source) {
    super(source);
    this.source = source;
  }

  public boolean executeOnce() {
    List<Event> events = new ArrayList<>();
    try{
      events = source.getEvents();
      System.out.println("Received event from "+ getName() + "count="+ events.size());
    } catch (Exception e){
      System.out.println("Failed to fetch events in "+ getName() );
      return false;
    }

    //Emit the event to outGoing Queue
    try{
    for(EventQueue queue: getOutGoingQueues()){
      queue.addAll(events);
    }
    } catch (Exception e){
      System.out.println("Failed to push events to outgoing Queue "+ getName() );
      return false;
    }
    return true;
  }
}
