package com.project.shoponline;

import java.security.SecureRandom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.project.shoponline.utils.RandomString;

@SpringBootApplication
@ComponentScan("com.project.shoponline")
public class ShoponlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoponlineApplication.class, args);
	}

}
