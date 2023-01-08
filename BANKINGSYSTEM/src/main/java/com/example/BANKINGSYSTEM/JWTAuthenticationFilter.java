package com.example.BANKINGSYSTEM;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;





///CALS TO VALIDATE THE INCOMING JWT TOKEN............
@Component("JWTAuthenticator")
public class JWTAuthenticationFilter extends OncePerRequestFilter 
{
	
	
	@Autowired
	private AdministratorDetails details;
	
	
	@Autowired
	private JWTUtil util;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException 
	{
		
		String tokenValue = request.getHeader("Authorization");
		
		System.out.println("AUTHENTICATING THE JWT TOKEN");
		System.out.println("Token Provided: " + tokenValue);
		if(tokenValue != null && tokenValue.startsWith("Bearer ") )
		{
			System.out.println("Token Provided: " + tokenValue);
			String token = tokenValue.substring(7);
			
			
			try
			{
				
				String username = this.util.getUsernameFromToken(token);
				      UserDetails s =  this.details.loadUserByUsername(username);
				      
				      
				      if(s != null && SecurityContextHolder.getContext().getAuthentication() == null)
				      {
				    	  
				    	  UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(s, null, s.getAuthorities());
				    	  auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request ));
				    	  SecurityContextHolder.getContext().setAuthentication(auth);
				    	  
				      }
				      else
				      {
				    	  org.slf4j.LoggerFactory.getLogger(JWTAuthenticationFilter.class).info("TOKEN NOT VALIDATED");
				      }
				      
				      
		
				
				
			  
			}
			catch(Exception exp)
			{
				exp.printStackTrace();
			}
			
			
			
						
		}
		
		
		System.out.println("GIVING PERMISSION TO ACCESS THE RESOURCES");
		try
		{
		filterChain.doFilter(request, response);
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
			System.out.println(exp);
		}

		
		
		
		
	}
	

}
