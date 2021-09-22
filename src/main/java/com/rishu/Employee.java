package com.rishu;

public class Employee extends AbstractResource {

  private String id;
  private String name;
  private Integer salary;
  private EmployeeType type;
  private String deptId;



  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Integer getSalary() {
    return salary;
  }

  @Override
  public Integer incrementSalary() {
    return null;
  }

  @Override
  public String getDeptId() {
    return deptId;
  }
}
