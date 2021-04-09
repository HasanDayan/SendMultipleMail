package com.hd.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SendMultipleMailApplication {

	public static void main(String[] args) {
		SpringApplication.run(SendMultipleMailApplication.class, args);
	}

}
