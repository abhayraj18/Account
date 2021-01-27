package com.example.form;

import javax.validation.constraints.NotEmpty;

public class AccountForm extends UserForm {

	@NotEmpty(message = "Please select account type")
	private String accountType;

	@NotEmpty(message = "Please enter customer name")
	private String customerName;

	@NotEmpty(message = "Please select branch")
	private String branch;

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

}
