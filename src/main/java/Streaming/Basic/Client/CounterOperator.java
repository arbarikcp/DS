package Streaming.Basic.Client;

import Streaming.Basic.Api.Model.Event;
import Streaming.Basic.Api.Model.Operator;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.yetus.audience.InterfaceAudience.Private;

public class CounterOperator extends Operator {

  private Map<String, Integer> frequencyCounter = new HashMap<>();
  public CounterOperator(String name) {
    super(name);
  }

  @Override
  public List<Event> apply(Event event)
  {
    String data = event.getData();
    frequencyCounter.put(data, frequencyCounter.getOrDefault(data, 0) + 1);
    System.out.println("counter till now "+ frequencyCounter);
    List<Event> eventList = new ArrayList<>();
    eventList.add(event);
    return eventList;
  }

}
