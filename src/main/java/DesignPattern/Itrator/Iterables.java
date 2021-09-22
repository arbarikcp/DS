package DesignPattern.Itrator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Iterables<T> {

  List<T> list;

  public Iterables(List<T> list) {
    this.list = new ArrayList<>(list);
  }

  public int getLength(){
    return list.size();
  }

  public void addElements(T s){
    list.add(s);
  }

  T getElement(Integer index){
    return list.get(index);
  }

  public EvenIterator getEventIterator(){
    return new EvenIterator(this);
  }

  static class EvenIterator<T> implements Iterator<T>{

    Iterables<T> stringIterable;
    int index;

    public EvenIterator(Iterables<T> stringIterable) {
      this.stringIterable = stringIterable;
      this.index = 0;
    }

    @Override
    public boolean hasNext() {
      return index < stringIterable.getLength();
    }

    @Override
    public T next() {
      if (hasNext()) {
        T val = stringIterable.getElement(index);
        index = index + 2;
        return val;
      }else{
        throw new NoSuchElementException("iterator has ended");
      }
    }
  }

  public static void main(String[] args) {
    Iterables<String> iter = new Iterables(Arrays.asList("abc", "def", "gh", "jk"));
    iter.addElements("hello");
    iter.addElements("world");
    EvenIterator eIt =  iter.getEventIterator();
    while (eIt.hasNext()){
      System.out.println("value is "+ eIt.next());
      iter.addElements("hello");
      iter.addElements("world");
    }
  }
}
