package Streaming.Basic.Client;

import Streaming.Basic.Api.Model.JobSpecification;
import Streaming.Basic.Api.Model.Operator;
import Streaming.Basic.Api.Model.Source;
import Streaming.Basic.Engine.Job.Job;

public class CounterJob {

  public static void main(String[] args) {

    Source source  = new NumberSource("NumberSource");
    Operator operator = new CounterOperator("counterOperator");

    Source socketSource = new SocketSource("SocketSource", 9999);
    Operator operator1 = new CounterOperator("counterOperator1");
    JobSpecification spec = new JobSpecification("Counter-Job");

    //Define job specification starting from Source
    source.getOutGoingStream().applyOperator(operator);
    socketSource.getOutGoingStream().applyOperator(operator1);

    //define job source
    spec.addSource(source);
    spec.addSource(socketSource);

    Job job = new Job(spec);
    job.start();
  }
}
