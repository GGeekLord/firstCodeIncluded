package com.ecommerceapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class EcomApp {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(EcomApp.class, args);


	}

}
