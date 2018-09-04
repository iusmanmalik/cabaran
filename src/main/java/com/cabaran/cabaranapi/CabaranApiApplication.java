package com.cabaran.cabaranapi;

import com.cabaran.cabaranapi.Models.Employee;
import com.cabaran.cabaranapi.Utils.JsonFileProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties(JsonFileProperties.class)
public class CabaranApiApplication {

    @Autowired private JsonFileProperties jsonFileProperties;


	public static void main(String[] args) {
		SpringApplication.run(CabaranApiApplication.class, args);
	}

	@Bean
	public boolean init() throws Exception {
        if(StringUtils.isBlank(jsonFileProperties.getFiledir()) || StringUtils.isBlank(jsonFileProperties.getFilename())) throw new Exception("Data Source json file not configured");

        Path dir = Paths.get(jsonFileProperties.getFiledir());

        boolean pathExists =
                Files.exists(Paths.get(jsonFileProperties.getFiledir() + File.separator + jsonFileProperties.getFilename()),
                        new LinkOption[]{ LinkOption.NOFOLLOW_LINKS});

        if(!pathExists){
            try {
                Files.createDirectories(dir);
                Files.createFile(Paths.get(jsonFileProperties.getFiledir() + File.separator + jsonFileProperties.getFilename()));
            } catch(FileAlreadyExistsException ex){
                ex.printStackTrace();
                // the directory already exists.
            } catch (IOException e) {
                //something else went wrong
                e.printStackTrace();
            }
// load some dummy data

            Employee employee = new Employee(1,"Usman Malik",25,6000);
            List<Employee> employees = new ArrayList<>();
            employees.add(employee);

            new ObjectMapper().writeValue(new File(jsonFileProperties.getFiledir() + File.separator + jsonFileProperties.getFilename()), employees);
        }



return true;
    }
}
