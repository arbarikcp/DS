package CoR;

public interface Handler {

  public void handle();
  public void setNextHandler(Handler next);

}
