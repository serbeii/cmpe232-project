package com.databeats.databeats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class DatabeatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabeatsApplication.class, args);
	}

}
