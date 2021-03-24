package Stack.Applications;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Infix {

  private final static Set<Character> operators = new HashSet<>(Arrays.asList('/', '*', '-', '+'));
  private final static String delimiter = " ";

  private String infixExpression;
  private String[] tokens;

  public Infix(String infixExpression) {
    this.infixExpression = infixExpression;
    tokens = this.infixExpression.split(delimiter);
  }

  public boolean validateParenthesis() {
    int openParenthesis = 0;
    int closeParenthesis = 0;
    for(String token: tokens){
      if(token.equals("(")){
        openParenthesis++;
      }
      if(token.equals( ")")){
        closeParenthesis++;
        if(closeParenthesis > openParenthesis){
          return false;
        }
      }
    }
    if(openParenthesis != closeParenthesis){
      return false;
    }
    return true;
  }
}
