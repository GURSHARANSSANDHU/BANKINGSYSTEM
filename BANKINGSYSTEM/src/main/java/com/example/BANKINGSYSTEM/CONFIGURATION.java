package com.example.BANKINGSYSTEM;

import org.apache.logging.log4j.spi.NoOpThreadContextMap;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//CONFIGURATION CLASS................
@SuppressWarnings("deprecation")
@Configuration
public class CONFIGURATION
{

	@Bean
	public Logger getLogger() {
		return org.slf4j.LoggerFactory.getLogger("BankingsystemApplication");
	}

	@SuppressWarnings("deprecation")
	@Bean("NoOptEncoder")
	public PasswordEncoder getNoOptEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean("BcryptEncoder")
	public PasswordEncoder getBcryptEncoder() {
		return new BCryptPasswordEncoder(10);
	}

	
}
