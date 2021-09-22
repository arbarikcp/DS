package Streaming.Basic.Engine.Executor;

import Streaming.Basic.Api.Model.Component;
import Streaming.Basic.Engine.Pipes.EventQueue;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public abstract class ComponentExecutor extends BaseExecutor {

  private Component component;
  @Getter
  @Setter
  protected EventQueue incomingQueue;  // Assumtion here is only one incoming Queue
  @Getter
  private List<EventQueue> outGoingQueues; // But multiple outgoing Queue. In reality we can have multiple incoming and outgoing Queue

  @Getter
  private final String name;

  public ComponentExecutor(Component component) {
    this.component = component;
    this.outGoingQueues = new ArrayList();
    this.name = this.component.getName()+"-Executor";
  }

  public void addOutGoingQueue(EventQueue queue){
    outGoingQueues.add(queue);
  }

}
