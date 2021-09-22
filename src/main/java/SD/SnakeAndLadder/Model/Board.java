package SD.SnakeAndLadder.Model;

import java.util.HashMap;
import java.util.Random;
import lombok.Getter;

public class Board {

  @Getter private int size;
  @Getter private Dice dice;
  private HashMap<Integer, Integer> snakes;
  private HashMap<Integer, Integer> ladders;

  public Board(int size, int countOfSnakes, int countOfLadder) {
    this.size = size;
    dice = new Dice();
    snakes = new HashMap<>();
    ladders = new HashMap<>();
    initializeBoard(countOfSnakes, countOfLadder);
  }

  private void initializeBoard(int countOfSnakes, int countOfLadder) {
    initializeSnakes(countOfSnakes);
    initializeLadder(countOfLadder);
  }

  private void initializeSnakes(int countOfSnake) {
    Random random = new Random(System.currentTimeMillis());
    int count = 0;
    while (count < countOfSnake) {
      int start = random.nextInt(size) + 1;
      int end = random.nextInt(size) + 1;
      if (isValidSnake(start, end)) {// Snake must be from up to down, end position can't be for snake or else no one can win.
        snakes.put(start, end);
        count++;
      }
    }
  }

  private void initializeLadder(int countOfLadder) {
    Random random = new Random(System.currentTimeMillis());
    int count = 0;
    while (count < countOfLadder) {
      int start = random.nextInt(size) + 1;
      int end = random.nextInt(size) + 1;
      //Start position can't be ladder
      //both snake and ladder can't have same start or end
      if (isValidLadder(start, end)) {
        ladders.put(start, end);
        count++;
      }
    }
  }

  private boolean isValidLadder(int start, int end){
    if (start < end && start != 0 && end != size && !snakes.containsKey(end)) {
      return true;
    }
    return false;
  }

  private boolean isValidSnake(int start, int end){
    if (start > end && end != size && start != size) {
      return true;
    }
    return false;
  }

  private boolean isValidMove(int current, int count) {
    if( current == 0 && count != 1 && count != 6){ // first move must be by 1 or 6
      return false;
    }

    return (current + count) <= size; // you can't go beyond board
  }

  public int getNewPosition(int current, int count) {
    if (isValidMove(current,count)) {
      int newPosition = current + count;
      if (snakes.containsKey(newPosition)) {
        newPosition = snakes.get(newPosition);
        System.out.println("------Bitten by snakes to " + newPosition);
      } else if (ladders.containsKey(newPosition)) {
        newPosition = ladders.get(newPosition);
        System.out.println("***** Climbed ladder to " + newPosition);
      }
      return newPosition;
    }else{
      System.out.println("Not a valid position="+ current + " count="+ count);
      return current;
    }

  }
}
