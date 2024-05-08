package com.java.example.tanzu.wherefordinner.trafficgen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WhereForDinnerTrafficGenApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhereForDinnerTrafficGenApplication.class, args);
	}

}
