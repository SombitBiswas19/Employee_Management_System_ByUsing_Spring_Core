package com;

import java.util.Scanner;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class EmployeeConfig {
	@Bean
	@Primary  //provides priority bcz IOC became confused because both returns same type.
	public Scanner scannerBean1() {
		return new Scanner(System.in);
	}
	@Bean
	public Scanner scannerBean2() {
		return new Scanner(System.in);
	}
}
