package SD.SnakeAndLadder.Model;

import lombok.Getter;
import lombok.Setter;

public class Player {

  @Getter
  String name;

  @Setter
  @Getter
  int current;
  @Setter
  boolean winner;

  public Player(String name) {
    this.name = name;
    this.current = 0;
    this.winner = false;
  }




}
