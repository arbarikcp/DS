package CoR;

public abstract class AbstractHandler implements Handler {

  Handler nextHandler;

  @Override
  public void setNextHandler(Handler next) {
    nextHandler = next;
  }

  public void next(){
    if(nextHandler != null){
      nextHandler.handle();
    }
  }

}
