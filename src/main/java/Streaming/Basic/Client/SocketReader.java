package Streaming.Basic.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketReader {

  private final BufferedReader reader;

  public SocketReader(int port) {
    reader = setupSocketReader(port);
  }

  public String getNextString(){
    try{
    String line = reader.readLine();
      return line;
    } catch (Exception e){
      return null;
    }
  }

  private BufferedReader setupSocketReader(int port) {
    try {
      Socket socket = new Socket("localhost", port);
      InputStream input = socket.getInputStream();
      return new BufferedReader(new InputStreamReader(input));
    } catch (UnknownHostException e) {
      e.printStackTrace();
      System.exit(0);
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(0);
    }
    return null;
  }
}
