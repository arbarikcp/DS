package Stack;

import java.util.Optional;

public interface StackInterface<T> {

  void push(T item) throws Exception;

  Optional<T> pop() throws Exception;

  int size();


}
