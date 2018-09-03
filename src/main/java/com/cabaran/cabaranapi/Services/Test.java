package com.cabaran.cabaranapi.Services;

import com.cabaran.cabaranapi.Models.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) throws Exception {

//        JSONObject obj = new JSONObject();
//        obj.put("name", "mkyong.com");
//        obj.put("age", new Integer(100));
//
//        JSONArray list = new JSONArray();
//        list.add("msg 1");
//        list.add("msg 2");
//        list.add("msg 3");
//
//        obj.put("messages", list);
//
//        try (FileWriter file = new FileWriter("test.json")) {
//
//            file.write(obj.toJSONString());
//            file.flush();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.print(obj);



        List<HashMap<String,?>> empMap = new ObjectMapper().readValue(new FileInputStream("test.json"),List.class);

        empMap.forEach(emp->{
//            Integer id = (Integer) emp.get("id");
//            System.out.println("id " + id);
//            System.out.println("fullName " + emp.get("fullName"));
//            System.out.println("age " + emp.get("age"));
//            System.out.println("salary " + emp.get("salary"));


        });

       List<Employee> list = empMap.stream().map(emp -> new Employee((Integer) emp.get("id"),(String)emp.get("fullName"),(Integer)  emp.get("age"), (Integer) emp.get("salary"))).collect(Collectors.toList());

        new ObjectMapper().writeValue(new File("employees.json"),list);

list.forEach(l-> System.out.println("" + l.getId()));

    }


}
