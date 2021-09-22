package com.rishu;

import java.util.HashMap;
import java.util.Map;

public class Organization {

  public String name;
  public Map<String, BusinessUnit> units;

  public Organization(String name) {
    this.name = name;
    units = new HashMap<>();
  }

  public void addBusinessUnit(BusinessUnit unit) {
    units.put(unit.getId(), unit);
  }

  public void removeBusinessUnit(BusinessUnit unit) {
    units.remove(unit.getId());
  }

  public String getName() {
    return name;
  }

  public Map<String, BusinessUnit> getUnits() {
    return units;
  }
}
