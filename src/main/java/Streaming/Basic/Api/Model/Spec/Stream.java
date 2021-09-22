package Streaming.Basic.Api.Model.Spec;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

public class Stream {

  @Getter
  private List<Operator> operatorList;

  public Stream() {
    this.operatorList = new ArrayList<Operator>();
  }

  public Stream applyOperator(Operator o){
    operatorList.add(o);
    return o.getOutGoingStream();
  }

}
