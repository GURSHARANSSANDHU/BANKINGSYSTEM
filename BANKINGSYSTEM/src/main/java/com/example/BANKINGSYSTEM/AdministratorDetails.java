package com.example.BANKINGSYSTEM;

import org.apache.naming.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Service
public class AdministratorDetails implements UserDetailsService {

	@Qualifier("SECURITYRepo")
	@Autowired
	private SecurityDAO d1;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("CCCCCCCCCCCCCC");
		User u = null;
		try
		{
		u = this.d1.getByUsername(username);
		if(u != null)
		{
		System.out.println("ROLE OF THE CLIENT IS: " + u.getRole());
		}
		if(u == null)
		{
			System.out.println("THE CLIENT IS NOT AUTHORIZED TO ACCESS THE RESOURCES");
			u = new User(100,"STEVE", "JOBS0001", "ROLE_NORMAL");
		}
		}
		catch(Exception exp)
		{
			exp.printStackTrace();
		}
		//System.out.println("VALUE OF THE RETREIVED USER IS: " + u.toString());
		//org.slf4j.LoggerFactory.getLogger(AdministratorDetails.class).info("LOGGER: " + u.toString());
		return new Administrator(u);
	}

}
