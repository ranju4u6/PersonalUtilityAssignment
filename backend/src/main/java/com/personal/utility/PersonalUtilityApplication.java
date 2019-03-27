package com.personal.utility;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"com.personal.utility"})
public class PersonalUtilityApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalUtilityApplication.class, args);
	}

}
