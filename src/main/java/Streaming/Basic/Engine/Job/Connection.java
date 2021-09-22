package Streaming.Basic.Engine.Job;

import Streaming.Basic.Engine.Executor.ComponentExecutor;
import Streaming.Basic.Engine.Executor.OperatorExecutor;
import lombok.Getter;

public class Connection {

  @Getter
  private final ComponentExecutor from;
  @Getter
  private final OperatorExecutor to;

  public Connection(ComponentExecutor from, OperatorExecutor to) {
    this.from = from;
    this.to = to;
  }
}
