package com.example.BANKINGSYSTEM;

import org.hibernate.validator.constraints.EAN;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableEurekaClient
@EnableSwagger2
@SpringBootApplication
public class BankingsystemApplication implements CommandLineRunner {

	@Autowired
	private Logger log;

	@Value("${server.port}")
	private String port;

	@Qualifier("SECURITYRepo")
	@Autowired
	private SecurityDAO d1;

	public static void main(String[] args) {
		SpringApplication.run(BankingsystemApplication.class, args);
	}
	
	@LoadBalanced
	@Bean
	public RestTemplate getRestTemplet()
	{
		return new RestTemplate();
	}

	@Override
	public void run(String... args) throws Exception {

		this.log.info("STARTED THE BANKING SYSTEM APPLICATION ON PORT: " + this.port);
		this.d1.save(new User(1, "GURSHARAN", new BCryptPasswordEncoder().encode("#Waheguru0001"), "ROLE_NORMAL"));

	}

}
