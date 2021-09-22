package SD.SnakeAndLadder.Model;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class Game {

    private Board board;
    private Queue<Player> players;

  public Game(Board board , List<Player> players) throws Exception {
    this.board = board;
    this.players = new ArrayDeque<>();
    for(Player p : players){
      this.players.offer(p);
    }
  }

  public void start() throws Exception {
    while (players.size() != 1){
      Thread.sleep(100);
      Player p = players.poll();
      Dice dice = board.getDice();
      int count = dice.roll();
      int newPosition = board.getNewPosition(p.getCurrent(),count);
      p.setCurrent(newPosition);
      System.out.println("Rolled Dice "+ count +",  " +p.getName() +" moved to "+ newPosition);
      if(newPosition == board.getSize()){
        p.setWinner(true);
        System.out.println("Won the game");
      }else{
        players.offer(p);
      }
    }
  }
}
