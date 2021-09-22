package Streaming.Basic.Api.Model.Spec;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import org.joda.time.DateTime;

public class Event {

  @Getter
  private DateTime eventTime;

  @Getter
  private String data;

  private List<EventState> states;

  public Event(String data) {
    this.data = data;
    this.eventTime = DateTime.now();
    this.states = new ArrayList<EventState>();
    this.states.add(EventState.NEW);
  }

  public void addState(EventState state){
    this.states.add(state);
  }

}
