package com.company.ordersystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderSystemApplication.class, args);
	}

	//TODO
	// - CONSTRUCTION:
	// 		- Controller interface
	// 			->  search
	// 			->  copy
	// 		- Move script to script folder ->  static/script file
	// - FUNCTIONALITY
	// 		- Sort order in order list by company
	//		- Sort product by All and product without productEng
	// - CAMPAIGN ADD STATUS TO DB

}
