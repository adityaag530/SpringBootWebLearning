package com.aditya.springbootwebtutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootWebTutorialApplication {

	//@ComponentScan in @SpringBootApplication - scans all beans and able to figure out which ones are controllers

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWebTutorialApplication.class, args);
	}

}
