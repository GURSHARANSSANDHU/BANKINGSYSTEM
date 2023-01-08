package com.example.BANKINGSYSTEMUSEROPERATIONS;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//DATAMODEL LAYER....................
@Entity
@Table(name = "BANK_CUSTOMERS")
@Data
@Getter
@Setter
@ToString
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	private String name;
	private int panNumber;
	private String address;

	@JoinColumn(name = "CUSTOMER_Id", referencedColumnName = "customerId")
	@OneToMany(targetEntity = Account.class, cascade = CascadeType.ALL)
	private List<Account> l1 = new ArrayList<Account>();

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(int panNumber) {
		this.panNumber = panNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Account> getL1() {
		return l1;
	}

	public void setL1(List<Account> l1) {
		this.l1 = l1;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", name=" + name + ", panNumber=" + panNumber + ", address="
				+ address + ", l1=" + l1 + "]";
	}

}
