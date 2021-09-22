package lombokdemo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.RequiredArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
//@RequiredArgsConstructor()
public class User {

  private int id;
  private String fistName;
  private String lastName;

  @Setter(AccessLevel.PRIVATE)
  private int age;

  @ToString.Exclude
  private boolean admin; //For all boolean it will generate isxxx ,    lombok.getter.noIsPrefix = true
  private Boolean sysAdmin; //Will generate getSysAdmin, setSysAdmin

  @Setter(AccessLevel.NONE)
  @Getter(AccessLevel.NONE)
  private int salary;

  @Getter(lazy=true)
  private final String loc = getLocation();


  private String getLocation(){
    System.out.println("Location is def");
    return "def";
  }

  public boolean isGreaterThanZero(@NonNull int data){
    return data > 0;
  }
}
