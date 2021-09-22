package Streaming.Basic.Api.Model;

import com.google.common.collect.Lists;
import java.util.List;

public class Operator extends Component {

  public Operator(String name) {
    super(name);
  }

  public List<Event> apply(Event event)
  {
    return Lists.newArrayList();
  };

}
