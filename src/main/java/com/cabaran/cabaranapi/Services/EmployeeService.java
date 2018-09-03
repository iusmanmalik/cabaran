package com.cabaran.cabaranapi.Services;

import com.cabaran.cabaranapi.Models.Employee;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

  private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public  List<Employee> employees;

    public EmployeeService() throws Exception {
        employees = getList();
    }


    public List<Employee> getList() throws Exception {
    List<HashMap<String, ?>> empMap =
        OBJECT_MAPPER.readValue(new FileInputStream("test.json"), List.class);
    return empMap
        .stream()
        .map(
            emp ->
                new Employee(
                    (Integer) emp.get("id"),
                    (String) emp.get("fullName"),
                    (Integer) emp.get("age"),
                    (Integer) emp.get("salary")))
        .collect(Collectors.toList());
  }

    public Employee get(Long id) throws Exception {
        List<HashMap<String, ?>> empMap =
                OBJECT_MAPPER.readValue(new FileInputStream("test.json"), List.class);
        return empMap
                .stream()
                .map(
                        emp ->
                                new Employee(
                                        (Integer) emp.get("id"),
                                        (String) emp.get("fullName"),
                                        (Integer) emp.get("age"),
                                        (Integer) emp.get("salary")))
                .filter(employee -> id.equals(employee.getId())).findAny().orElse(new Employee());
    }



//    lt – less than
//    lte – less than equal
//    gt – greater than
//    ge – greater than equal
//    eq – equal
//    ne – not equal

//    asc – Ascending
//    desc – Descending
    public List<Employee> filterByAge(String operator,Integer value,String sort) throws Exception{
        List<Employee> employees = new ArrayList<>();
        List<HashMap<String, ?>> empMap =
                OBJECT_MAPPER.readValue(new FileInputStream("test.json"), List.class);

        switch (operator) {
            case "lt":
                return empMap
                        .stream()
                        .map(
                                emp ->
                                        new Employee(
                                                (Integer) emp.get("id"),
                                                (String) emp.get("fullName"),
                                                (Integer) emp.get("age"),
                                                (Integer) emp.get("salary"))).filter(employee -> employee.getAge() < value).sorted(Comparator.comparing(Employee::getAge))
                        .collect(Collectors.toList());

            case "lte":
                return empMap
                        .stream()
                        .map(
                                emp ->
                                        new Employee(
                                                (Integer) emp.get("id"),
                                                (String) emp.get("fullName"),
                                                (Integer) emp.get("age"),
                                                (Integer) emp.get("salary"))).filter(employee -> employee.getAge() <= value).sorted(Comparator.comparing(Employee::getAge))
                        .collect(Collectors.toList());

            case "gt":
                return empMap
                        .stream()
                        .map(
                                emp ->
                                        new Employee(
                                                (Integer) emp.get("id"),
                                                (String) emp.get("fullName"),
                                                (Integer) emp.get("age"),
                                                (Integer) emp.get("salary"))).filter(employee -> employee.getAge() > value).sorted(Comparator.comparing(Employee::getAge))
                        .collect(Collectors.toList());
            case "ge":
                return empMap
                        .stream()
                        .map(
                                emp ->
                                        new Employee(
                                                (Integer) emp.get("id"),
                                                (String) emp.get("fullName"),
                                                (Integer) emp.get("age"),
                                                (Integer) emp.get("salary"))).filter(employee -> employee.getAge() <= value).sorted(Comparator.comparing(Employee::getAge))
                        .collect(Collectors.toList());
            case "eq":
                return empMap
                        .stream()
                        .map(
                                emp ->
                                        new Employee(
                                                (Integer) emp.get("id"),
                                                (String) emp.get("fullName"),
                                                (Integer) emp.get("age"),
                                                (Integer) emp.get("salary"))).filter(employee -> employee.getAge() == value).sorted(Comparator.comparing(Employee::getAge))
                        .collect(Collectors.toList());
            case "ne":
                return empMap
                        .stream()
                        .map(
                                emp ->
                                        new Employee(
                                                (Integer) emp.get("id"),
                                                (String) emp.get("fullName"),
                                                (Integer) emp.get("age"),
                                                (Integer) emp.get("salary"))).filter(employee -> employee.getAge() != value).sorted(Comparator.comparing(Employee::getAge))
                        .collect(Collectors.toList());


            default:
                logger.info(
                        "Invalid operator");
                return employees;
        }
    }

  public void save() throws Exception {
    OBJECT_MAPPER.writeValue(new File("test.json"), employees);
  }

}
