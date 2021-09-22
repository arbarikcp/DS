package SD.SnakeAndLadder;

import SD.SnakeAndLadder.Model.Board;
import SD.SnakeAndLadder.Model.Game;
import SD.SnakeAndLadder.Model.Player;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {


  public static void main(String[] args) throws Exception {

    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter Board size  ");
    int boardSize = scanner.nextInt();

    System.out.print("Enter Snake count  ");
    int snakeCount = scanner.nextInt();

    System.out.print("Enter Ladder count  ");
    int ladderCount = scanner.nextInt();

    System.out.print("Enter Player count  ");
    int playerCount = scanner.nextInt();

    List<Player> playerList = new ArrayList<>();

    for(int index = 0; index < playerCount; index++){
      playerList.add(new Player("P-"+ index));
    }

    Board board = new Board(boardSize, snakeCount, ladderCount);

    Game game = new Game(board, playerList);
    game.start();
  }


}
