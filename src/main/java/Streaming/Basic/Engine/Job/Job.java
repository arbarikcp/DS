package Streaming.Basic.Engine.Job;

import Streaming.Basic.Api.Model.Component;
import Streaming.Basic.Api.Model.JobSpecification;
import Streaming.Basic.Api.Model.Operator;
import Streaming.Basic.Api.Model.Source;
import Streaming.Basic.Engine.Executor.ComponentExecutor;
import Streaming.Basic.Engine.Executor.OperatorExecutor;
import Streaming.Basic.Engine.Executor.SourceExecutor;
import Streaming.Basic.Engine.Pipes.EventQueue;
import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.List;

//Convert a job specification to List of Executors and its connections
public class Job {

  JobSpecification spec;
  private List<ComponentExecutor> executors;
  private List<Connection> connections;

  public Job(JobSpecification spec) {
    this.spec = spec;
    this.executors = Lists.newArrayList();
    this.connections = Lists.newArrayList();
    createJob();
  }

  public void start(){
    Collections.reverse(executors);
    for(ComponentExecutor executor: executors){
      executor.start();
    }
  }

  private void createJob(){

    List<Source> sources = spec.getSources();
    for(Source source: sources){
      SourceExecutor sourceExecutor = new SourceExecutor(source);
      executors.add(sourceExecutor);
      traverseJobDAG(sourceExecutor, source);
    }
    connectExecutors();

  }

  private void traverseJobDAG(ComponentExecutor componentExecutor, Component component){
    List<Operator> operators = component.getOutGoingStream().getOperatorList();
    for(Operator operator: operators){
      OperatorExecutor operatorExecutor = new OperatorExecutor(operator);
      executors.add(operatorExecutor);
      connections.add(new Connection(componentExecutor,operatorExecutor));
      traverseJobDAG(operatorExecutor, operator);
    }
  }

  private void connectExecutors(){
    for(Connection connection: connections){
      EventQueue connectionQueue = new EventQueue(100);
      connection.getFrom().addOutGoingQueue(connectionQueue);
      connection.getTo().setIncomingQueue(connectionQueue);
    }
  }

}
