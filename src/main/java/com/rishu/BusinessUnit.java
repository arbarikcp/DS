package com.rishu;

import java.util.List;
import java.util.Map;

public class BusinessUnit extends AbstractResource {

  private String id;
  private String name;
  private Resource hod;
  private Map<String,Resource> employees;
  private Integer totalExpense;

  public BusinessUnit(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public boolean addEmployee(Resource employee){
    if(!employees.containsKey(employee.getId())){
      employees.put(employee.getId(), employee);
      totalExpense += employee.getSalary();
    }
    return false;
  }

  public boolean removeEmployee(Resource employee){
    if(!employees.containsKey(employee.getId())){
      employees.remove(employee.getId());
      totalExpense -= employee.getSalary();
    }
    return false;
  }

  public boolean releaseIncrement(Resource employee){
    if(employees.containsKey(employee.getId())){
      Resource resource = employees.get(employee.getId());
      resource.incrementSalary();
      return true;
    }else{
      return false;
    }
  }

  public void setName(String name) {
    this.name = name;
  }

  public Resource getHod() {
    return hod;
  }

  public void setHod(Resource hod) {
    this.hod = hod;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Integer incrementSalary() {
    return null;
  }

  @Override
  public Integer getExpenditure(){
    return totalExpense;
  }
}
