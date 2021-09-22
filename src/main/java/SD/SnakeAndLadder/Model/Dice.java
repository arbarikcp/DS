package SD.SnakeAndLadder.Model;

import java.util.Random;

public class Dice {
  int max;

  public Dice() {
    max =6;
  }

  public int roll(){
    return new Random().nextInt(max) + 1;
  }

}
