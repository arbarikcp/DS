package Streaming.Basic.Engine.Executor;

import Streaming.Basic.Api.Model.Event;
import Streaming.Basic.Api.Model.Operator;
import Streaming.Basic.Engine.Pipes.EventQueue;
import java.util.List;

public class OperatorExecutor extends ComponentExecutor {

  private final Operator operator;

  public OperatorExecutor(Operator operator) {
    super(operator);
    this.operator = operator;
  }

  public boolean executeOnce() {
    try {
      Event event = (Event) incomingQueue.take();
      System.out.println("get the event in "+ getName() + "event="+ event);
      List<Event> processedEvent = operator.apply(event);
      for(EventQueue outGoingQueue : getOutGoingQueues()){
        outGoingQueue.addAll(processedEvent);
      }
    } catch (Exception e) {
      System.out.println("failure in " + getName());
      return false;
    }
    return true;
  }
}
