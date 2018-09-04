package com.cabaran.cabaranapi;

import com.cabaran.cabaranapi.Controllers.EmployeeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CabaranApiApplication.class)
@AutoConfigureMockMvc
public class CabaranApiApplicationTests {

  @Autowired private MockMvc mockMvc;

  @Autowired private EmployeeController controller;

  @Test
  public void shouldReturnDefaultMessage() throws Exception {
    this.mockMvc
        .perform(get("/ping"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("running")));
  }

  @Test
  public void contexLoads() throws Exception {
    assertThat(controller).isNotNull();
  }
}
