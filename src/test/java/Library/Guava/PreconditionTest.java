package Library.Guava;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import com.google.common.base.Preconditions;
import org.junit.Test;

public class PreconditionTest {

  @Test
  public void whenCheckArgumentEvaluatesFalse_throwsException() {
    int age = -18;
    assertThatThrownBy(() -> Preconditions.checkArgument(age > 0))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage(null).hasNoCause();
  }

  @Test
  public void givenErrorMsg_whenCheckArgEvalsFalse_throwsException() {
    int age = -18;
    String message = "Age can't be zero or less than zero.";

    assertThatThrownBy(() -> Preconditions.checkArgument(age > 0, message))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage(message).hasNoCause();
  }

  @Test
  public void givenTemplateMsg_whenCheckArgEvalsFalse_throwsException() {
    int age = -18;
    String message = "Age should be positive number, you supplied %s.";

    //Preconditions.checkArgument(age > 0, message, age);

    assertThatThrownBy(
        () -> Preconditions.checkArgument(age > 0, message, age))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage(message, age).hasNoCause();
  }

  @Test
  public void givenArrayAndMsg_whenCheckElementEvalsFalse_throwsException() {
    int[] numbers = { 1, 2, 3, 4, 5 };
    String message = "Please check the bound of an array and retry";

    //Preconditions.checkElementIndex(6, numbers.length -1, message);

    assertThatThrownBy(() ->
        Preconditions.checkElementIndex(6, numbers.length - 1, message))
        .isInstanceOf(IndexOutOfBoundsException.class)
        .hasMessageStartingWith(message).hasNoCause();
  }

  @Test
  public void givenArrayAndMsg_whenCheckPositionEvalsFalse_throwsException() {
    int[] numbers = { 1, 2, 3, 4, 5 };
    String message = "Please check the bound of an array and retry";
    Preconditions.checkPositionIndexes(10, 5,numbers.length - 1);
    assertThatThrownBy(
        () -> Preconditions.checkPositionIndex(6, numbers.length - 1, message))
        .isInstanceOf(IndexOutOfBoundsException.class)
        .hasMessageStartingWith(message).hasNoCause();

    assertThatThrownBy(
        () -> Preconditions.checkPositionIndexes(-1, 5,numbers.length - 1))
        .isInstanceOf(IndexOutOfBoundsException.class)
        .hasMessageStartingWith(message).hasNoCause();
  }

  @Test
  public void givenNullString_whenCheckNotNullWithMessage_throwsException () {
    String nullObject = null;
    String message = "Please check the Object supplied, its null!";

    //Preconditions.checkNotNull(nullObject, message);
    assertThatThrownBy(() -> Preconditions.checkNotNull(nullObject, message))
        .isInstanceOf(NullPointerException.class)
        .hasMessage(message).hasNoCause();
  }

  @Test
  public void whenCheckNotNullWithTemplateMessage_throwsException() {
    String nullObject = null;
    String message = "Please check the Object supplied, its %s!";

    Preconditions.checkNotNull(nullObject, message, new Object[]{"args","args1", null});
    assertThatThrownBy(
        () -> Preconditions.checkNotNull(nullObject, message,
            new Object[] { null }))
        .isInstanceOf(NullPointerException.class)
        .hasMessage(message, nullObject).hasNoCause();
  }

}
