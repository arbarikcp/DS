package SD.JsonParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {

  public static char LEFT_BRACE = '{';
  public static  char RIGHT_BRACE = '}';
  public static char RIGHT_BRACKET = ']';
  public static char LEFT_BRACKET = '[';
  public static char COLON = ':';
  public static char COMMA =',';
  public static char QUOTE ='"';
  public static Character[] NUMBERS={'0','1','2','3','4','5','6','7','8','9','e','-'};
  public static Character[] WHITESPACE={' ','\t','\n','\r','\b'};
  public static Character[] JSON_CHARS={LEFT_BRACE,RIGHT_BRACE,LEFT_BRACKET,RIGHT_BRACKET,COLON,COMMA};
  public static List<Character> JSON_CHAR_LIST = Arrays.asList(JSON_CHARS);
  public static List<Character> NUMBERS_LIST= Arrays.asList(NUMBERS);
  public static List<Character> WHITESPACE_LIST= Arrays.asList(WHITESPACE);

}
