package Streaming.Basic.Engine.Pipes;

import java.util.concurrent.ArrayBlockingQueue;

public class EventQueue extends ArrayBlockingQueue {

  public EventQueue(int capacity) {
    super(capacity);
  }
}
