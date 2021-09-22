package SD.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

  private String str;

  public Lexer(String str) {
    this.str = str;
  }

  public static void main(String[] args) throws Exception {
    String str = "\"jsontoken\"";
    String num = "12345\"jsontoken\"";
    Lexer lex = new Lexer(num);
    System.out.println(lex.getNumberToken(0));
    System.out.println(lex.getStringToken(0));

    String json_str = "{\"foo\":[1,2,\"three\"]}";
    Lexer json_lex = new Lexer(json_str);
    List<TokenObject> tokens = json_lex.lex(json_str);
    System.out.println(tokens);
  }

  private TokenObject getStringToken(int startIndex) throws Exception {
    String token = null;
    StringBuilder builder = new StringBuilder();
    int index = startIndex;
    if (startIndex < str.length() - 1 && str.charAt(startIndex) == Constants.QUOTE) {
      index++;
      for (; index < str.length() && str.charAt(index) != Constants.QUOTE; index++) {
        Character c = str.charAt(index);
        builder.append(c);
      }
      token = builder.toString();
      if (index < str.length() && str.charAt(index) == Constants.QUOTE) {
        return new TokenObject(token, index+1);
      } else {
        throw new Exception("Invalid character at " + index);
      }
    } else {
      return new TokenObject(null, startIndex);
    }
  }

  private TokenObject getNumberToken(int startIndex) {

    int index = startIndex;
    StringBuilder builder = new StringBuilder();
    if (startIndex < str.length()) {
      for (; index < str.length(); index++) {
        Character c = str.charAt(index);
        if (Constants.NUMBERS_LIST.contains(c)) {
          builder.append(c);
        } else {
          break;
        }
      }
      String token = builder.toString();
      if (token.length() != 0) {
        if (token.contains(".")) {
          return new TokenObject(Double.parseDouble(token), index);
        } else {
          return new TokenObject(Integer.parseInt(token), index);
        }
      }
    }
    return new TokenObject(null, startIndex);
  }

  public List<TokenObject> lex(String str) throws Exception {

    List<TokenObject> tokens = new ArrayList<>();
    int index = 0;
    while (index < str.length()) {
      TokenObject tokenObject = getStringToken(index);
      if (tokenObject.token != null) {
        tokens.add(tokenObject);
        index = tokenObject.endIndex;
        continue;
      }

      tokenObject = getNumberToken(index);
      if (tokenObject.token != null) {
        tokens.add(tokenObject);
        index = tokenObject.endIndex;
        continue;
      }

      if (Constants.WHITESPACE_LIST.contains(str.charAt(index))) {
        index++;
      } else if (Constants.JSON_CHAR_LIST.contains(str.charAt(index))) {
        tokens.add(new TokenObject(str.charAt(index), index + 1));
        index++;
      } else {
        throw new Exception("Invalid character at " + index);
      }
    }

    return tokens;
  }

  class TokenObject {
    Object token;
    int endIndex;

    public TokenObject(Object token, int endIndex) {
      this.token = token;
      this.endIndex = endIndex;
    }

    @Override
    public String toString() {
      return token+"";
    }
  }
}
