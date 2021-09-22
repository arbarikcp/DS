package com.rishu;

public abstract class AbstractResource implements Resource{

  @Override
  public Integer getSalary() {
    return -1;
  }

  @Override
  public Integer getExpenditure() {
    return -1;
  }

  @Override
  public String getDeptId() {
    return null;
  }
}
