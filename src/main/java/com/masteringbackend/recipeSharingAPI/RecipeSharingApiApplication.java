package com.masteringbackend.recipeSharingAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class RecipeSharingApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipeSharingApiApplication.class, args);
	}

}
