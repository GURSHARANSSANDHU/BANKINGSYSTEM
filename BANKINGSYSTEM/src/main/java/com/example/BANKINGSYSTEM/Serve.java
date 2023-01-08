package com.example.BANKINGSYSTEM;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.sun.net.httpserver.Headers;

//SERVICE LAYER, BUSSINESS LAYER, APPLICATION LAYER
@Service("serviceClass")
public class Serve

{
	@Qualifier("BANKING_REPOSITORY")
	@Autowired
	private DAO d1;

	// CREATE to create a cutomer profile....................
	public ResponseEntity<String> create(Customer c) throws UserDefinedException {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

		if (c != null) {
			this.d1.save(c);
			map.add("code", "200");
			map.add("status", "true");
			map.add("message", "SUCCESS");
			HttpHeaders headers = new HttpHeaders(map);
			HttpStatus status = HttpStatus.ACCEPTED;
			ResponseEntity<String> response = new ResponseEntity<String>(
					"THE SPECIFIED CUSTOMER HAS BEEN INSERTED INTO THE DB", headers, status);
			return response;
		}

		else {
			throw new UserDefinedException("NO CUSTOMER IS SPECIFIED TO BE INSERTED IN TO THE DB");
		}

	}

	// CREATE to add the account corresponding to a specific
	// customer............................
	public ResponseEntity<String> addAccount(int id, Account a) throws UserDefinedException {
		List<Customer> l1 = this.d1.findAll();
		Customer c = null;
		for (Customer p : l1) {
			if (p.getCustomerId() == id) {
				c = p;
				break;
			}
		}
		if (c == null) {

			throw new UserDefinedException("THE SPECIFIED CUSTOMER IS NOT PRESENT IN THE DATABASE");
		} else {
			c.getL1().add(a);
			this.d1.save(c);
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
			map.add("code", "200");
			map.add("message", "SUCCESS");
			map.add("status", "true");
			HttpHeaders headers = new HttpHeaders(map);
			HttpStatus status = HttpStatus.ACCEPTED;
			return new ResponseEntity<String>("THE ACCOUNT HAS BEEN ADDED CORRESPONDING TO THE SPECIFIED CUSTOMER",
					headers, status);
		}

	}

	// UPDATE .... to update the balance in the account of the
	// customer.......................
	public ResponseEntity<String> updateAccountBalance(int custId, int accNumber, Long balance)
			throws UserDefinedException {
		Customer result = null;
		String message = null;
		List<Customer> l1 = this.d1.findAll();
		for (Customer p : l1) {
			if (p.getCustomerId() == custId) {
				result = p;
				break;
			}
		}
		if (result == null) {

			throw new UserDefinedException("THE SPECIFIED CUSTOMER IS NOT PRESENT IN THE DB");
		}

		else {
			Account c = null;
			int v = -1;
			for (Account a : result.getL1()) 
			{
				v = v + 1;
				if (a.getAccountNumber() == accNumber) {
					
					c = a;
					break;
				}
			}

			if (c == null) {

				throw new UserDefinedException(
						"THE SPECIFIED CUSTOMER IS NOT MAPPED WITH THE GIVEN CORRESPONDING ACCOUNT");
			} else {
				c.setAccountBalance(balance);
				result.getL1().set(v, c);
				this.d1.save(result);
				message = "THE ACCOUNT OF THE SPECIFIED CUSTOMER HAS BEEN UPDATED";
				MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
				map.add("code", "200");
				map.add("status", "true");
				map.add("message", "SUCCESS");
				HttpHeaders headers = new HttpHeaders(map);
				HttpStatus status = HttpStatus.ACCEPTED;
				return new ResponseEntity<String>(message, headers, status);

			}
		}

	}

	// READ read one
	public ResponseEntity<Customer> readOne(int custId) throws UserDefinedException {
		List<Customer> l1 = this.d1.findAll();
		Customer r = null;
		for (Customer c : l1) {
			if (c.getCustomerId() == custId) {
				r = c;
				break;
			}
		}
		if (r == null) {

			throw new UserDefinedException("THE SPECIFIED CUSTOMR IS NOT PRESENT IN THE DATABASE");

		} else {
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
			map.add("code", "200");
			map.add("message", "SUCCESS");
			map.add("status", "true");
			HttpHeaders headers = new HttpHeaders(map);
			HttpStatus status = HttpStatus.ACCEPTED;
			return new ResponseEntity<Customer>(r, headers, status);
		}
	}

	// READ ALL ......read all.............
	public ResponseEntity<String> getAll() throws UserDefinedException {
		List<Customer> l1 = this.d1.findAll();
		if (l1 == null) {

			throw new UserDefinedException("DATABASE IS EMPTY");
		} else {
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
			map.add("code", "200");
			map.add("status", "true");
			map.add("message", "SUCCESS");
			HttpHeaders headers = new HttpHeaders(map);
			HttpStatus status = HttpStatus.ACCEPTED;
			return new ResponseEntity<String>(l1.toString(), headers, status);

		}

	}

	// DELETE ONE...........
	public ResponseEntity<String> deleteOne(int id) throws UserDefinedException {
		List<Customer> l1 = this.d1.findAll();
		Customer result = null;
		for (Customer c : l1) {
			if (c.getCustomerId() == id) {
				result = c;
				break;
			}
		}

		if (result == null) {

			throw new UserDefinedException("THE SPECIFIED CUSTOMER IS NOT PRESENT IN THE DB");
		}

		else {
			l1.remove(result);
			this.d1.delete(result);
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
			map.add("code", "200");
			map.add("message", "SUCCESS");
			map.add("status", "true");
			HttpHeaders headers = new HttpHeaders(map);
			HttpStatus status = HttpStatus.ACCEPTED;
			return new ResponseEntity<String>("THE SPECIFIED CUTOMER HAS BEEN DELEETED FROM THE DB", headers, status);
		}
	}

	public ResponseEntity<String> removeAccount(int custId, int accountno) throws UserDefinedException {
		List<Customer> l1 = this.d1.findAll();
		Customer r = null;
		for (Customer c : l1) {
			if (c.getCustomerId() == custId) {
				r = c;
				break;
			}
		}

		if (r == null) {

			throw new UserDefinedException("THE SPECIFIED CUSTOMER IS NOT PRESENT IN THE DB");

		} else {
			Account ar = null;

			for (Account a : r.getL1()) {
				if (a.getAccountNumber() == accountno) {
					ar = a;
					break;
				}
			}

			if (ar == null) {

				throw new UserDefinedException("THE SPECIFIED ACCOUNT DOES NOT BELONGS TO THE SPECaIFIED CUSTOMER");
			} else {
				r.getL1().remove(ar);
				this.d1.save(r);
				MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
				map.add("status", "true");
				map.add("code", "200");
				map.add("message", "SUCCESS");
				HttpHeaders headers = new HttpHeaders(map);
				HttpStatus status = HttpStatus.ACCEPTED;
				return new ResponseEntity<String>("THE SPECIFIED ACCOUNT HAS BEEN DELETED FROM THE DB", headers,
						status);
			}
		}
	}

}
