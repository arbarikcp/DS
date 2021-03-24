package ElementarySort;


import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class SortTest {

  private Student[] getRandomStudentObject(int count){

    Student[] allStudents = new Student[count];
    for(int i = 0; i < count; i++){
      int age = i % 18;
      int grade = age - 6 < 0 ? 0 : age - 6;
      int section = i % 5;
      String name = TestUtils.generateRandomString();
      allStudents[i] = new Student(name,age,section,grade);
    }
    return allStudents;
  }

  @Test
  public void testSelectionSort(){
    Student[] allStudents = getRandomStudentObject(100);
    System.out.println(Arrays.toString(allStudents));
    SortAlgorithms.selectionSort(allStudents);
    System.out.println(Arrays.toString(allStudents));
    Assert.assertTrue(SortUtil.isSorted(allStudents));
  }

  @Test
  public void testSelectionSort_With_Comparator(){
    Student[] allStudents = getRandomStudentObject(100);
    System.out.println(Arrays.toString(allStudents));
    SortAlgorithms.selectionSortWithComparator(allStudents, Student.NAME_BASED_COMPARATOR);
    System.out.println(Arrays.toString(allStudents));
    Assert.assertTrue(SortUtil.isSorted(allStudents,Student.NAME_BASED_COMPARATOR));
  }

  @Test
  public void testSelectionSort_With_Comparator_1(){
    Student[] allStudents = getRandomStudentObject(100);
    System.out.println(Arrays.toString(allStudents));
    SortAlgorithms.selectionSortWithComparator(allStudents, Student.AGE_BASED_COMPARATOR);
    System.out.println(Arrays.toString(allStudents));
    Assert.assertTrue(SortUtil.isSorted(allStudents,Student.AGE_BASED_COMPARATOR));
  }


  @Test
  public void testInsertionSort_With_Comparator_1(){
    Student[] allStudents = getRandomStudentObject(200);
    System.out.println(Arrays.toString(allStudents));
    SortAlgorithms.insertionSortWithComparator(allStudents, Student.AGE_BASED_COMPARATOR);
    System.out.println(Arrays.toString(allStudents));
    Assert.assertTrue(SortUtil.isSorted(allStudents,Student.AGE_BASED_COMPARATOR));
  }

  @Test
  public void testInsertionSort_StabilityTest(){
    Student[] allStudents = getRandomStudentObject(200);
    System.out.println(Arrays.toString(allStudents));
    SortAlgorithms.insertionSortWithComparator(allStudents, Student.NAME_BASED_COMPARATOR);
    SortAlgorithms.insertionSortWithComparator(allStudents, Student.AGE_BASED_COMPARATOR);
    System.out.println(Arrays.toString(allStudents));
    //Assert.assertTrue(SortUtil.isSorted(allStudents,Student.AGE_BASED_COMPARATOR));

  }
}
