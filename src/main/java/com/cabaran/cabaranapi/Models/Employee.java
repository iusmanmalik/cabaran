package com.cabaran.cabaranapi.Models;

import com.cabaran.cabaranapi.Utils.Utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Objects;

public class Employee {

  private long id;
  private String fullName;
  private Integer age;
  private BigDecimal salary;

  public Employee(String fullName, Integer age, Integer salary) {
    this.id = Utils.generateRandomNumber(4);
    this.fullName = fullName;
    this.age = age;
    this.salary = new BigDecimal(salary);
  }

  public Employee(Integer id, String fullName, Integer age, Integer salary) {
    this.id = id;
    this.fullName = fullName;
    this.age = age;
    this.salary = new BigDecimal(salary);
  }

  public Employee(HashMap<String, ?> emp) {
    this.id = (Integer) emp.get("id");
    this.fullName = (String) emp.get("fullName");
    this.age = (Integer) emp.get("age");
    this.salary = new BigDecimal((Integer) emp.get("salary"));
  }

  public Employee() {}

  public long getId() {
    return id;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public Integer getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public BigDecimal getSalary() {
    return salary;
  }

  public void setSalary(BigDecimal salary) {
    this.salary = salary;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Employee employee = (Employee) o;
    return id == employee.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, fullName, age, salary);
  }
}
