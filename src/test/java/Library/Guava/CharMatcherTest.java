package Library.Guava;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.google.common.base.CharMatcher;
import com.google.common.base.Predicate;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import org.junit.Assert;
import org.junit.Test;

public class CharMatcherTest {

  @Test
  public void whenRemoveSpecialCharacters_thenRemoved() {
    String input = "H*el.lo,}12"; // String implements CharSequence Interface.
    CharMatcher matcher = CharMatcher.noneOf("*.,}");
    String result = matcher.retainFrom(input);
    System.out.println(result);
    assertEquals("Hello12",result);
    }

  @Test
  public void whenRemoveCharsNotInCharset_thenRemoved() {
    Charset charset = Charset.forName("cp437");
    CharsetEncoder encoder = charset.newEncoder();
    Predicate<Character> inRange = c -> encoder.canEncode(c);
    String result = CharMatcher.forPredicate(inRange).retainFrom("helloは");
    assertEquals("hello", result);

    }

  @Test
  public void whenValidateString_thenValid(){
    String input = "hello";


    boolean result = CharMatcher.javaLowerCase().matchesAllOf(input);
    assertTrue(result);

    result = CharMatcher.is('e').matchesAnyOf(input);
    assertTrue(result);

    result = CharMatcher.javaDigit().matchesNoneOf(input);
    assertTrue(result);
  }
}
