package com.example.BANKINGSYSTEMUSEROPERATIONS;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;



@EnableHystrixDashboard
@EnableHystrix
@EnableEurekaClient
@SpringBootApplication
public class BankingsystemuseroperationsApplication implements CommandLineRunner
{

	public static void main(String[] args) {
		SpringApplication.run(BankingsystemuseroperationsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception 
	{
		
		LoggerFactory.getLogger(BankingsystemuseroperationsApplication.class).info("STARTING BANKING SYSTEM USER APPLICATION!!!!!!!!!");
		
	}
	
	
	
	@LoadBalanced
	@Bean
	@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public RestTemplate getTemplate()
	{
		return new RestTemplate();
	}

}
