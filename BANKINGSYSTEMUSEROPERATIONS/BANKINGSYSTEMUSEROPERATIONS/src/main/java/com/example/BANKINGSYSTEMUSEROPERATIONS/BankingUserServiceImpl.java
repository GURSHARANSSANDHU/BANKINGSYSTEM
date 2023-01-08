package com.example.BANKINGSYSTEMUSEROPERATIONS;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.apache.http.HttpStatus;
import org.apache.http.auth.Credentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class BankingUserServiceImpl {

	private static final Logger logger = LoggerFactory.getLogger(BankingUserServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	// READ ONE................
	// TO GET THE DETAILS OF A PARTICULER CUSTOMER;
	public ResponseEntity<Customer> getOneCustomer(Integer custId) throws UserDefinedException {

		ResponseEntity<Customer> response = null;

		// RestTemplate restTemplate = new RestTemplate();

		try {
			JWTCredentials credentials = new JWTCredentials();
			credentials.setUsername("GURSHARAN");
			credentials.setPassword("#Waheguru0001");
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

			ResponseEntity<String> tokenResponse = this.restTemplate.postForEntity(
					"http://localhost:3333/bankingApplication/generate/token", credentials, String.class);

			logger.info("Token RESPONSE OBTAINED IS: " + tokenResponse.toString());

			String tokenResponseBody = tokenResponse.getBody().split(":")[1];
			String token = "Bearer " + tokenResponseBody.substring(1, tokenResponseBody.length() - 2);

			logger.info("TOKEN BEING SENT FOR VERIFICATION IS: " + token);
			map.add("Authorization", token);
			HttpHeaders headers = new HttpHeaders(map);
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			logger.info(entity.toString());

			response = this.restTemplate.exchange("http://localhost:3333/bankingApplication/get/one?custId=" + custId,
					HttpMethod.GET, entity, Customer.class);

			logger.info("FINAL RESPONSE OBTAINED: " + response.toString());

		}

		catch (Exception exp) {
			exp.printStackTrace();
			throw new UserDefinedException(exp.getMessage());
		}

		return response;

	}

	// READ ALL....................
	public ResponseEntity<String> getAll() throws UserDefinedException {

		ResponseEntity<String> response = null;

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();

		JWTCredentials cred = new JWTCredentials();
		cred.setUsername("GURSHARAN");
		cred.setPassword("#Waheguru0001");

		try {

			ResponseEntity<String> tokenResponse = this.restTemplate
					.postForEntity("http://localhost:3333/bankingApplication/generate/token", cred, String.class);

			logger.info("TOKEN RESPONSE OBTAINED IS: " + tokenResponse.toString());
			String subToken = tokenResponse.getBody().split(":")[1];

			String t = subToken.substring(1, subToken.length() - 2);

			String token = "Bearer " + t;

			logger.info("JWT token being sent to the BANKING SYSTEM is: " + token);

			map.add("Authorization", token);

			HttpHeaders headers = new HttpHeaders(map);
			HttpEntity<String> entity = new HttpEntity<String>(headers);

			logger.info("HEADERS BEING SENT TO THE BANKING SYSTEM ARE: " + entity.toString());
			response = this.restTemplate.exchange("http://localhost:3333/bankingApplication/get/all", HttpMethod.GET,
					entity, String.class);

			logger.info("RESPONSE OBTAINED FROM BANKING SYSTEM is: " + response.toString());
		} catch (Exception exp) {
			exp.printStackTrace();
			throw new UserDefinedException(exp.getMessage());
		}

		return response;

	}

	// CREATE ONE.........................
	public ResponseEntity<String> createAccount(Customer c) throws UserDefinedException {
		JWTCredentials cred = new JWTCredentials();
		cred.setUsername("GURSHARAN");
		cred.setPassword("#Waheguru0001");
		try {
			ResponseEntity<String> tokenResponse = this.restTemplate
					.postForEntity("http://localhost:3333/bankingApplication/generate/token", cred, String.class);

			this.logger.info("Response token obtained friom the banking system is: " + tokenResponse.toString());
			String t = tokenResponse.getBody().split(":")[1];
			String subtoken = t.substring(1, t.length() - 2);

			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
			String token = "Bearer " + subtoken;

			logger.info("Token to be sent to the BANKING SYSTEMN is: " + token);
			map.add("Authorization", token);
			HttpHeaders headers = new HttpHeaders(map);
			HttpEntity<Customer> entity = new HttpEntity<Customer>(c, headers);

			logger.info("Headers to be sent to the BANKING SYSTEM IS: " + entity.toString());

			ResponseEntity<String> response = this.restTemplate.exchange(
					"http://localhost:3333/bankingApplication/create/customer", HttpMethod.POST, entity, String.class);

			return response;
		} catch (Exception exp) {
			exp.printStackTrace();
			throw new UserDefinedException(exp.getMessage());
		}

	}

	// TO ADD AN ACCOUNT TO A CORRESPONDING CUSTOMER...............
	public ResponseEntity<String> addAccount(int custId, Account a) throws UserDefinedException {
		ResponseEntity<String> response = null;
		JWTCredentials cred = new JWTCredentials();
		cred.setUsername("GURSHARAN");
		cred.setPassword("#Waheguru0001");
		try {
			ResponseEntity<String> tokenResponse = this.restTemplate
					.postForEntity("http://localhost:3333/bankingApplication/generate/token", cred, String.class);
			logger.info("Token response obtained is: " + tokenResponse.toString());

			String t = tokenResponse.getBody().split(":")[1];
			String token = "Bearer " + t.substring(1, t.length() - 2);
			logger.info("Token being sent to the BANKING SYSTEM IS: " + token);
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
			map.add("Authorization", token);
			HttpHeaders headers = new HttpHeaders(map);
			logger.info("HEADERS BEING SENT TO THE BANKING SYSTEM ARE: " + headers.toString());
			HttpEntity<Account> entity = new HttpEntity<Account>(a, headers);
			logger.info("entity being sent to the BANKING SYSTEM IS: " + entity.toString());
			response = this.restTemplate.exchange("http://localhost:3333/bankingApplication/add/account?id=" + custId,
					HttpMethod.PUT, entity, String.class);

			logger.info("final response obtained from the BANKING SYSTEM is: " + response.toString());

		} catch (Exception exp) {
			exp.printStackTrace();
			throw new UserDefinedException(exp.getMessage());
		}

		return response;

	}

	public ResponseEntity<String> updateAccountBalance(int custId, int account, int balance)
			throws UserDefinedException {

		ResponseEntity<String> response = null;
		JWTCredentials cred = new JWTCredentials();
		cred.setUsername("GURSHARAN");
		cred.setPassword("#Waheguru0001");

		try {
			ResponseEntity<String> tokenResponse = this.restTemplate
					.postForEntity("http://localhost:3333/bankingApplication/generate/token", cred, String.class);
			logger.info("Token response obtained from the banking system is: " + tokenResponse.toString());

			String t = tokenResponse.getBody().split(":")[1];
			String token = "Bearer " + t.substring(1, t.length() - 2);

			logger.info("Token to be sent to the banking system is: " + token);
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
			map.add("Authorization", token);
			HttpHeaders headers = new HttpHeaders(map);

			logger.info("Headers being sent to the BANKING SYSTEM ARE: " + headers.toString());
			HttpEntity<String> entity = new HttpEntity<String>(map);

			logger.info("ENTITY BEING SENT TO THE BANKING SYSTEM IS: " + entity.toString());

			response = this.restTemplate.exchange("http://localhost:3333/bankingApplication/update/account/balance/"
					+ custId + "/" + account + "/" + balance, HttpMethod.PUT, entity, String.class);
			logger.info("FINAL RESPONSE OBTAINED FROM BANKING SYSTEM IS: " + response.toString());
		} catch (Exception exp) {
			exp.printStackTrace();
			throw new UserDefinedException(exp.getMessage());
		}

		return response;

	}

	@HystrixCommand(groupKey = "FUNDS TRANSFER", commandKey = "FUNDS TRANSFER", fallbackMethod = "fundsTransferFaultTolerance")
	public ResponseEntity<String> transferFunds(int sender, int recipient, int balance, int src, int dest)
			throws UserDefinedException {
		JWTCredentials cred = new JWTCredentials();
		cred.setUsername("GURSHARAN");
		cred.setPassword("#Waheguru0001");
		try {

			ResponseEntity<String> tokenResponse = this.restTemplate
					.postForEntity("http://localhost:3333/bankingApplication/generate/token", cred, String.class);

			this.logger.info("token response obtained from BANKING System IS: " + tokenResponse.toString());

			String t = tokenResponse.getBody().split(":")[1];

			String token = "Bearer " + t.substring(1, t.length() - 2);
			logger.info("Token to be sent to the BANKING SYSTEM IS: " + token);
			MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
			map.add("Authorization", token);
			HttpHeaders headers = new HttpHeaders(map);
			logger.info("Headers to sent to the BANKING SYSTEM are: " + headers.toString());

			HttpEntity<String> entity = new HttpEntity<String>(headers);
			ResponseEntity<Customer> senderResponse = this.restTemplate.exchange(
					"http://localhost:3333/bankingApplication/get/one?custId=" + sender, HttpMethod.GET, entity,
					Customer.class);

			Customer c = senderResponse.getBody();

			Account senderAccount = null;
			for (Account a : c.getL1()) {
				if (a.getAccountNumber() == src) {
					senderAccount = a;
					break;
				}
			}

			if (senderAccount == null) {
				throw new UserDefinedException("SENDERS ACCOUNT NOT PRESENT IS");
			}

			else {

				if (senderAccount.getAccountBalance() < balance) {
					throw new UserDefinedException("THE SENDER IS NOT HAVING ENOUGH BALANCE IN HIS ACCOUNT");
				}
				ResponseEntity<Customer> recipientResponse = this.restTemplate.exchange(
						"http://localhost:3333/bankingApplication/get/one?custId=" + recipient, HttpMethod.GET, entity,
						Customer.class);

				Account recipientAccount = null;
				for (Account a : c.getL1()) {
					if (a.getAccountNumber() == dest) {
						recipientAccount = a;
						break;
					}

				}

				if (recipientAccount == null) {
					throw new UserDefinedException("the destination account is not present in the db");
				}

				else {
					long sendersBalance = senderAccount.getAccountBalance() - balance;
					long recipientbalance = recipientAccount.getAccountBalance() + balance;

					ResponseEntity<String> sendersResponse = this.restTemplate.exchange(
							"http://localhost:3333/bankingApplication/update/account/balance/ " + sender + "/"
									+ senderAccount.getAccountNumber() + "/" + sendersBalance,
							HttpMethod.PUT, entity, String.class);

					this.logger.info("ACCOUNT BALANCE OF THE SENDER UPDATED with the response as: "
							+ sendersResponse.toString());

					ResponseEntity<String> recipientsResponse = this.restTemplate.exchange(
							"http://localhost:3333/bankingApplication/update/account/balance/ " + recipient + "/"
									+ recipientAccount.getAccountNumber() + "/" + recipientbalance,
							HttpMethod.PUT, entity, String.class);

					this.logger.info(
							"ACCOUNT OF THE RECIPIENT UPDATED with response as: " + recipientsResponse.toString());

					MultiValueMap<String, String> finalMap = new LinkedMultiValueMap<String, String>();
					finalMap.add("status", "true");
					finalMap.add("code", "200");
					finalMap.add("message", "FUNDS TRANSFER SUCCESFULL");

					finalMap = new HttpHeaders(finalMap);

					ResponseEntity<String> response = new ResponseEntity<String>(
							"THE FUNDS HAVE BEEN TRANSFERED SUCCESFULLY", headers, HttpStatus.SC_ACCEPTED);

					return response;

				}

			}
		} catch (Exception exp) {
			exp.printStackTrace();
			throw new UserDefinedException(exp.getMessage());
		}

	}

	// TRANSFER FUNDS FALLBACK METHOD.................
	// fundsTransferFaultTolerance
	public ResponseEntity<String> fundsTransferFaultTolerance(int sender, int recipient, int balance, int src,
			int dest) {
		ResponseEntity<String> response = new ResponseEntity<String>(
				"ERROR OCCURED......PLEASE TRY AGAIN LATER........",
				org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
		return response;
	}

}
