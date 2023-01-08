package com.example.BANKINGSYSTEMAPIGATEWAY;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//to make this api gateway a client of the eureka server.............................
@EnableEurekaClient
@SpringBootApplication
public class BankingsystemapigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingsystemapigatewayApplication.class, args);
	}

}
