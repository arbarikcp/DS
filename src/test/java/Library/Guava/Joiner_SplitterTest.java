package Library.Guava;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Joiner.MapJoiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class Joiner_SplitterTest {

  @Test
  public void whenConvertListToString_thenConverted() {
    List<String> names = Lists.newArrayList("John", "Jane", "Adam", "Tom");
    String result = Joiner.on(",").join(names);
    assertEquals(result, "John,Jane,Adam,Tom");
  }

  @Test
  public void whenConvertMapToString_thenConverted() {
    Map<String, Integer> salary = Maps.newHashMap();
    salary.put("John", 1000);
    salary.put("Jane", 1500);

    String result = Joiner.on(" , ").withKeyValueSeparator(" = ") //returns MapJoiner
        .join(salary);

    assertThat(result, containsString("John = 1000"));
    assertThat(result, containsString("Jane = 1500"));
    Joiner.MapJoiner mapJoiner = Joiner.on(", ").withKeyValueSeparator("=");
    System.out.println(mapJoiner.join(salary));
  }


  @Test
  public void whenJoinNestedCollections_thenJoined() {
    List<ArrayList<String>> nested = Lists.newArrayList(
        Lists.newArrayList("apple", "banana", "orange"),
        Lists.newArrayList("cat", "dog", "bird"),
        Lists.newArrayList("John", "Jane", "Adam"));
    String result = Joiner.on(";").join(Iterables.transform(nested,
        new Function<List<String>, String>() {
          @Override
          public String apply(List<String> input) {
            return Joiner.on("-").join(input);
          }
        }));

    assertThat(result, containsString("apple-banana-orange"));
    assertThat(result, containsString("cat-dog-bird"));
    assertThat(result, containsString("apple-banana-orange"));
  }


  @Test
  public void whenCreateListFromString_thenCreated() {
    String input = "apple - banana - orange";
    List<String> result = Splitter.on("-").trimResults()
        .splitToList(input);

    System.out.println(result);
  }
  @Test
  public void whenCreateMapFromString_thenCreated() {
    String input = "John=first,Adam=second";
    Map<String, String> result = Splitter.on(",")
        .withKeyValueSeparator("=") // Returns MapSplitter
        .split(input);

    assertEquals("first", result.get("John"));
    assertEquals("second", result.get("Adam"));
  }


  @Test
  public void whenSplitStringOnMultipleSeparator_thenSplit() {
    String input = "apple.banana,,orange,,.";
    List<String> result = Splitter.onPattern("[.,]")
        .omitEmptyStrings()
        .splitToList(input);

  }
}
