package Streaming.Basic.Engine.Executor;

public abstract class BaseExecutor {

  private Thread thread;

  public BaseExecutor() {
    this.thread = new Thread(){
      @Override
      public void run(){
        while (executeOnce());
      }
    };

  }

  public void start(){
    this.thread.start();
  }

  public abstract boolean executeOnce();

}
