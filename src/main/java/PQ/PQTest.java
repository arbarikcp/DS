package PQ;


import ElementarySort.Student;
import java.util.PriorityQueue;
import java.util.Random;

public class PQTest {

  public static void main(String[] args) {
    PriorityQueue<Student> studentPriorityQueue = new PriorityQueue<>(Student.AGE_BASED_COMPARATOR);
    for (Student s : getRandomStudentObject(100)){
      studentPriorityQueue.add(s);
    }

    while (!studentPriorityQueue.isEmpty()){
      System.out.println(studentPriorityQueue.poll());
    }

  }

  private static Student[] getRandomStudentObject(int count){

    Student[] allStudents = new Student[count];
    for(int i = 0; i < count; i++){
      int age = i % 18;
      int grade = age - 6 < 0 ? 0 : age - 6;
      int section = i % 5;
      String name =  generateRandomString();
      allStudents[i] = new Student(name,age,section,grade);
    }
    return allStudents;
  }


  public static String generateRandomString() {
    int leftLimit = 97; // letter 'a'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 10;
    Random random = new Random();

    String generatedString = random.ints(leftLimit, rightLimit + 1)
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();

    return generatedString;
  }
}
