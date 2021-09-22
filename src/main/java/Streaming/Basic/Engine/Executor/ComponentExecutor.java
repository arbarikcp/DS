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
  protected EventQueue incomingQueue;
  @Getter
  private List<EventQueue> outGoingQueues;

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
