package Streaming.Basic.Client;

import Streaming.Basic.Api.Model.Event;
import Streaming.Basic.Api.Model.Source;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.joda.time.DateTime;

public class NumberSource extends Source {

  public NumberSource(String name) {
    super(name);
  }

  @Override
  public List<Event> getEvents(){
    List<Event> events = new ArrayList<>();
    try{
      Thread.sleep(10000);
      Random random = new Random(DateTime.now().getMillis());
      for (int i = 0; i < 3; i++) {
        events.add(new Event(String.valueOf(random.nextInt() % 20)));
      }
      return events;
    } catch (Exception e){

    }
    return events;
  }

}
