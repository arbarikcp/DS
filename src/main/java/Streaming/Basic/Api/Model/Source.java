package Streaming.Basic.Api.Model;

import com.google.common.collect.Lists;
import java.util.List;

public class Source extends Component {

  public Source(String name) {
    super(name);
  }

  public List<Event> getEvents(){
    return Lists.newArrayList();
  }

}
