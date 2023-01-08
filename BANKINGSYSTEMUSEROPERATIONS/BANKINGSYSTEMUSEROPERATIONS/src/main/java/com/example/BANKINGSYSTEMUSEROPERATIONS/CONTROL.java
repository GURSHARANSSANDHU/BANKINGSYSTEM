package com.example.BANKINGSYSTEMUSEROPERATIONS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CONTROL {

	@Autowired
	private BankingUserServiceImpl serve;

	@GetMapping("/get/one/customer")
	public ResponseEntity<Customer> getCustomer(@RequestParam(name = "custId") int custId) throws UserDefinedException {
		System.out.println("REQUEST LANDED WITH CUSTOMER ID: " + custId);
		ResponseEntity<Customer> response = this.serve.getOneCustomer(custId);
		return response;
	}

	@GetMapping("get/all/users")
	public ResponseEntity<String> getAll() throws UserDefinedException {
		ResponseEntity<String> response = this.serve.getAll();
		return response;
	}

	@PostMapping("create/customer")
	public ResponseEntity<String> createCustomer(@RequestBody Customer c) throws UserDefinedException {
		ResponseEntity<String> response = this.serve.createAccount(c);

		return response;
	}

	@PutMapping("/update/add/account/{custId}")
	public ResponseEntity<String> updateAddAccount(@PathVariable int custId, @RequestBody Account a)
			throws UserDefinedException {
		ResponseEntity<String> response = this.serve.addAccount(custId, a);
		return response;
	}

	@PatchMapping("/update/account/balance/{custId}/{account}/{balance}")
	public ResponseEntity<String> updateAccountBalance(@PathVariable int custId, @PathVariable int account,
			@PathVariable int balance) throws UserDefinedException {
		ResponseEntity<String> response = this.serve.updateAccountBalance(custId, account, balance);
		return response;
	}

	@PutMapping("/transfer/funds")
	public ResponseEntity<String> transferFunds(@RequestParam(name = "sender") int sender,
			@RequestParam(name = "receiver") int receiver, @RequestParam(name = "sendersAccount") int sendersAccount,
			@RequestParam(name = "recipientsAccount") int recipientsAccount, @RequestParam(name = "funds") int funds)
			throws UserDefinedException {
		ResponseEntity<String> response = this.serve.transferFunds(sender, receiver, funds, sendersAccount,
				recipientsAccount);
		return response;
	}

}
