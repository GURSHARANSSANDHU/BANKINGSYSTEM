package com.example.BANKINGSYSTEM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;

import net.bytebuddy.asm.Advice.This;

@SuppressWarnings("deprecation")
@EnableWebSecurity
@Configuration
public class CustomizeSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("NoOptEncoder")
	private PasswordEncoder noOpEncoder;

	@Autowired
	@Qualifier("BcryptEncoder")
	private PasswordEncoder bycryptEncoder;

	@Autowired
	private AdministratorDetails details;

	@Autowired
	@Qualifier("JWTAuthenticator")
	private JWTAuthenticationFilter filter;

	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;

	@Override
	public void configure(HttpSecurity security) throws Exception {

		// to implement basic spring security authentication.......
		// security.authorizeRequests().anyRequest().authenticated().and().httpBasic();

		security.csrf().disable().cors().disable().authorizeRequests()

				.antMatchers("/bankingApplication/generate/token").permitAll()
				.antMatchers("/bankingApplication/create/customer").hasRole("NORMAL")
				.antMatchers("/bankingApplication/get/one").hasRole("NORMAL").anyRequest().authenticated().and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				/* .httpBasic().and() */.exceptionHandling().authenticationEntryPoint(entryPoint);

		    security.addFilterBefore(this.filter, UsernamePasswordAuthenticationFilter.class);
		// UsernamePasswordAuthenticationFilter.class);
		// security.addFilterAfter(new CsrfTokenResponseHeaderBindingFilter(),
		// CsrfFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		System.out.println("SPRING SECURITY AUTHENTICATION");
		auth.userDetailsService(this.details).passwordEncoder(this.bycryptEncoder);

	}

	@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	@Bean
	public AuthenticationManager getAuthenticationManager() throws Exception {
		return super.authenticationManagerBean();

	}

}
