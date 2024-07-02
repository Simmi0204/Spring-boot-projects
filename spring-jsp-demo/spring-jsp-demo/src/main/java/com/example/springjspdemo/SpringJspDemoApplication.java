package com.example.springjspdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.springjspdemo")
public class SpringJspDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJspDemoApplication.class, args);
	}

}
