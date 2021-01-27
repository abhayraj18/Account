package com.example.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "ACCOUNT")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 50, name = "ACCOUNT_TYPE")
	private String accountType;

	@Column(length = 15, name = "CUSTOMER_NAME")
	private String customerName;

	@Column(length = 55, name = "BRANCH")
	private String branch;

	@Column(name = "OPEN_DATE")
	private LocalDate openDate;

	@Column(name = "MINOR", length = 1)
	private String minor;

	@Column(name = "CUSTOMER_ID", nullable = false)
	private Integer customerId;

	protected Account() {
	}

	public Account(String accountType, String customerName, String branch, String minor,
			Integer customerId) {
		this.accountType = accountType;
		this.customerName = customerName;
		this.branch = branch;
		this.openDate = LocalDate.now();
		this.minor = minor;
		this.customerId = customerId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public LocalDate getOpenDate() {
		return openDate;
	}

	public void setOpenDate(LocalDate openDate) {
		this.openDate = openDate;
	}

	public String getMinor() {
		return minor;
	}

	public void setMinor(String minor) {
		this.minor = minor;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

}
