package ElementarySort;

import java.util.Arrays;
import java.util.SortedMap;
import java.util.TreeMap;

public class SortClient {

  public static void main(String[] args) {

    String[] s = new String[]{"def", "xabc", "abe", "efg", "eas", "dws", "ebd"};

    String[] sortedArray = new String[]{"abe", "def", "dws", "eas", "ebd", "efg", "xabc"};
    String[] partialSortedArray = new String[]{"abe", "def", "dws", "ebd", "efg", "xabc", "eas"};
    String[] partialSortedArray_1 = new String[]{"def", "dws", "ebd", "efg", "xabc", "eas", "abe"};

    String[] partialSortedArray_2 = new String[]{"abe", "def", "eas", "dws", "ebd", "efg", "xabc"};

    SortAlgorithms.selectionSort(s);
    SortAlgorithms.insertionSort(s);
    SortAlgorithms.shellSort(s);

    System.out.println(s);

    SortAlgorithms.selectionSort(partialSortedArray_2);
    SortAlgorithms.insertionSort(partialSortedArray_2);
    SortAlgorithms.shellSort(partialSortedArray_2);

    System.out.println(partialSortedArray_2);

    SortAlgorithms.selectionSort(partialSortedArray_1);
    SortAlgorithms.insertionSort(partialSortedArray_1);
    SortAlgorithms.shellSort(partialSortedArray_1);

    System.out.println(partialSortedArray_1);

    SortUtil.shuffle(s);
    System.out.println(Arrays.toString(s));
    SortUtil.shuffle(s);
    System.out.println(Arrays.toString(s));
    SortUtil.shuffle(s);
    System.out.println(Arrays.toString(s));
  }

}
