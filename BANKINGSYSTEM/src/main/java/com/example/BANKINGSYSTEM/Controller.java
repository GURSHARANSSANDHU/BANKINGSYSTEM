package com.example.BANKINGSYSTEM;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/bankingApplication")
public class Controller {

	// to create an object of service class serve as an instance variable...
	@Qualifier("serviceClass")
	@Autowired
	private Serve s1;

	@Autowired
	private AdministratorDetails details;

	@Autowired
	@Qualifier("JWTUtil")
	private JWTUtil util;

	@Autowired
	private AuthenticationManager manager;

	@PostMapping("/create/customer")
	public ResponseEntity<String> createCustomer(@RequestBody Customer c) throws UserDefinedException {
		ResponseEntity<String> response = this.s1.create(c);
		LoggerFactory.getLogger(Controller.class).info("the response being retuerned is: " + response);
		System.out.println("CCCCCCC" + response.getBody());
		return response;

	}

	@PutMapping("/add/account")
	public ResponseEntity<String> addAccount(@RequestParam(name = "id") int id, @RequestBody Account a)
			throws UserDefinedException {
		return this.s1.addAccount(id, a);
	}

	@PutMapping("/update/account/balance/{id}/{accNo}/{balance}")
	public ResponseEntity<String> updateAccountBalance(@PathVariable int id, @PathVariable int accNo,
			@PathVariable long balance) throws UserDefinedException {
		return this.s1.updateAccountBalance(id, accNo, balance);
	}

	@GetMapping("/get/one")
	public ResponseEntity<Customer> getOne(@RequestParam(name = "custId") int custId) throws UserDefinedException {
		return this.s1.readOne(custId);
	}

	@GetMapping("/get/all")
	public ResponseEntity<String> readAll() throws UserDefinedException {
		return this.s1.getAll();
	}

	@DeleteMapping("delete/customer/{id}")
	public ResponseEntity<String> deleteOne(int id) throws UserDefinedException {
		return this.s1.deleteOne(id);
	}

	@PutMapping("/delete/account")
	public ResponseEntity<String> deleteAccount(@RequestParam(name = "custId") int custId,
			@RequestParam(name = "accountNo") int accountNo) throws UserDefinedException {
		return this.s1.removeAccount(custId, accountNo);
	}

	@RequestMapping(value = "/generate/token", method = RequestMethod.POST)
	public ResponseEntity<?> getToken(@RequestBody JWTCredentials c) throws Exception {
		System.out.println("REQUEST RECEIVED: " + c.toString());
		try {
			LoggerFactory.getLogger(Controller.class).info(
					"USER TRYING TO LOGIN WITH USERNAME: " + c.getUsername() + " and PASSWORD: " + c.getPassword());
			this.manager.authenticate(new UsernamePasswordAuthenticationToken(c.getUsername(), c.getPassword()));
		} catch (Exception exp)

		{
			return new ResponseEntity<String>("THE ENTERED CREDENTIALS ARE NOT CORRECT", HttpStatus.FORBIDDEN);
		}

		UserDetails u = this.details.loadUserByUsername(c.getUsername());
		String token = this.util.generateToken(u);
		LoggerFactory.getLogger(Controller.class).info("GENERATED JWT TOKEN IS: " + token);

		JWTTokenResponse t = new JWTTokenResponse(token);
		return new ResponseEntity<JWTTokenResponse>(t, HttpStatus.ACCEPTED);
	}

}
