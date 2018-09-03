package com.cabaran.cabaranapi.Controllers;

import com.cabaran.cabaranapi.Constants;
import com.cabaran.cabaranapi.Models.Employee;
import com.cabaran.cabaranapi.Services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class EmployeeController {


    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService employeeService;


    @RequestMapping("/ping")
    String test() {
        return "running";
    }


//    {"id": 1, "fullName": "Employee A", "age": 24, "salary": 4500}

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> list() {
        try {

            logger.info("listing emplyee");
            return  new ResponseEntity<>(employeeService.getList(), HttpStatus.OK);
        } catch (Exception e) {
            logger.info("error occurred while creating transaction");
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY); //422
        }
    }


    @RequestMapping(value = "/{employeeId}", method = RequestMethod.GET)
    public ResponseEntity<?> getEmployee(@PathVariable Long employeeId) {
        try {

            logger.info("listing emplyee");

            return  new ResponseEntity<>(employeeService.get(employeeId),HttpStatus.OK);
        } catch (Exception e) {
            logger.info("error occurred while creating transaction");
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY); //422
        }
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createEmployee(@RequestBody HashMap<String, ?> request) {
        try {

            logger.info("creating employee");
            String fullName = (String) request.get(Constants.FULL_NAME);
            Integer age = (Integer) request.get(Constants.AGE);
            Integer salary = (Integer)  request.get(Constants.SALARY);

            employeeService.employees.add(new Employee(fullName,age,salary));
            employeeService.save();

            return  new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.info("error occurred while creating transaction");
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY); //422
        }
    }

    @RequestMapping(value = "/{employeeId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateEmployee(@PathVariable Long employeeId,@RequestBody HashMap<String, ?> request) {
        try {

            logger.info("creating emplyee");
            String fullName = (String) request.get(Constants.FULL_NAME);
            Integer age = (Integer) request.get(Constants.AGE);
            Integer salary = (Integer)  request.get(Constants.SALARY);

            return  new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.info("error occurred while creating transaction");
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY); //422
        }
    }

    @RequestMapping(value = "/{employeeId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable Long employeeId) {
        try {

            logger.info("deleting emplyee");
            employeeService.employees.remove(employeeService.get(employeeId));
            employeeService.save();

            return  new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.info("error occurred while creating transaction");
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY); //422
        }
    }

//    {“operator”: “lt”, “value”: 30, “sort”: “asc”}
    @RequestMapping(value = "/filterByAge", method = RequestMethod.POST)
    public ResponseEntity<?> filterByAge(@RequestBody HashMap<String, ?> request) {
        try {

            String operator = (String) request.get(Constants.OPERATOR);
            Integer value = (Integer) request.get(Constants.VALUE);
            String sort = (String)  request.get(Constants.SORT);
            logger.info("listing employee");
            return  new ResponseEntity<>(employeeService.filterByAge(operator,value,sort), HttpStatus.OK);
        } catch (Exception e) {
            logger.info("error occurred while creating transaction");
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY); //422
        }
    }
}
