package com.realProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
@SpringBootApplication(scanBasePackages = "com.realProject")
public class RealApplication {

	public static void main(String[] args) {
		SpringApplication.run(RealApplication.class, args);
	}

}
