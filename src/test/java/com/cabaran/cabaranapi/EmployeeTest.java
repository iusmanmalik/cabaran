package com.cabaran.cabaranapi;

import com.cabaran.cabaranapi.Services.EmployeeService;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CabaranApiApplication.class)
@AutoConfigureMockMvc
public class EmployeeTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private EmployeeService transactionServiceMock;

  @Before
  public void setup() {}

  //    {"id": 1, "fullName": "Employee A", "age": 24, "salary": 4500},
  @Test
  public void createEmployee() throws Exception {

    JSONObject jsonObj = new JSONObject();

    jsonObj.put("fullName", "Usman Malik");
    jsonObj.put("age", 29);
    jsonObj.put("salary", 1000);
    String body = jsonObj.toString();

    // expected status = 201 if Employee created
    mockMvc
        .perform(post("/").content(body).contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().isCreated());
  }

  @After
  public void cleanUp() {}
}
