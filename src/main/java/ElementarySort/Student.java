package ElementarySort;

import java.util.Comparator;

public class Student implements Comparable<Student> {

  private String name;
  private int age;
  private int section;
  private int grade;
  public static Comparator<Student> NAME_BASED_COMPARATOR = new nameComparator();
  public static Comparator<Student> AGE_BASED_COMPARATOR = new ageComparator();
  public static Comparator<Student> GRADE_BASED_COMPARATOR = new gradeComparator();
  public static Comparator<Student> SECTION_BASED_COMPARATOR = new sectionComparator();

  public Student(String name, int age, int section, int grade) {
    this.name = name;
    this.age = age;
    this.section = section;
    this.grade = grade;
  }

/*
  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", age=" + age +
        ", section=" + section +
        ", grade=" + grade +
        '}';
  }
*/

  @Override
  public String toString() {
    return name+"-"+String.valueOf(age) ;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public int getSection() {
    return section;
  }

  public int getGrade() {
    return grade;
  }

  @Override
  public int compareTo(Student o) {
    return this.name.compareTo(o.name);
  }

  static class nameComparator implements Comparator<Student>{
    @Override
    public int compare(Student o1, Student o2) {
      return o1.name.compareTo(o2.name);
    }
  }

  static class ageComparator implements Comparator<Student>{
    @Override
    public int compare(Student o1, Student o2) {
      if(o1.age > o2.age) return 1;
      else if(o1.age < o2.age) return -1;
      else return 0;
    }
  }


  static class gradeComparator implements Comparator<Student>{
    @Override
    public int compare(Student o1, Student o2) {
      if(o1.grade > o2.grade) return 1;
      else if(o1.grade < o2.grade) return -1;
      else return 0;
    }
  }

  static class sectionComparator implements Comparator<Student>{
    @Override
    public int compare(Student o1, Student o2) {
      if(o1.section > o2.section) return 1;
      else if(o1.section < o2.section) return -1;
      else return 0;
    }
  }
}
