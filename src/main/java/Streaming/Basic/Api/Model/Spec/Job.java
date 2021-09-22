package Streaming.Basic.Api.Model.Spec;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;


public class Job {

  @Getter
  private String name;

  @Getter
  private List<Source> sources;

  public Job(String name) {
    this.name = name;
    this.sources = new ArrayList<Source>();
  }

  public void addSource(Source source){
    this.sources.add(source);
  }

  public void print(){
    for(Source source: sources){
      System.out.println();
      printJob(source,new StringBuilder());
    }
  }

  public void printJob(Component component, StringBuilder builder){
    System.out.print(component.getName()+"->");
    int jobNameLength  = component.getName().length();
    for(int  i = 0; i < jobNameLength + 2; i++){
      builder.append("-");
    }
    Stream outStream = component.getOutGoingStream();
    List<Operator> operators = outStream.getOperatorList();
    for(Operator operator : operators){
      printOperator(operator);
      System.out.println();
      System.out.print(builder.toString());
    }
  }

  public void printOperator(Operator operator){
    System.out.print(operator.getName()+"->");
    Stream outStream = operator.getOutGoingStream();
    List<Operator> operators = outStream.getOperatorList();
    for(Operator nextOperator : operators){
      printOperator(nextOperator);
    }
  }

}
