/**
 * 
 */
package com.example.service.impl;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.form.AccountForm;
import com.example.form.UserForm;
import com.example.model.Account;
import com.example.repository.AccountRepository;
import com.example.service.AccountService;
import com.example.service.RestAPIWebClient;
import com.example.util.DateUtil;
import com.example.vo.UserVO;

/**
 * @author abhay.jain
 *
 */
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private RestAPIWebClient webClient;

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public void createAccount(AccountForm form) {
		UserForm userForm = new UserForm();
		userForm.setDateOfBirth(form.getDateOfBirth());
		userForm.setGender(form.getGender());
		userForm.setPhoneNumber(form.getPhoneNumber());
		userForm.setUserName(form.getUserName());
		userForm.setUserType(form.getUserType());
		UserVO userVO = webClient.createUser(userForm);
		LocalDate dateOfBirth = DateUtil.parseStringToLocalDate(userVO.getDateOfBirth(), DateUtil.DD_MM_YYYY_FORMAT);
		int age = 19;
		if (Objects.nonNull(dateOfBirth)) {
			age = DateUtil.calculateAge(dateOfBirth);
		}
		Account account = new Account(form.getAccountType(), form.getCustomerName(), form.getBranch(),
				age <= 18 ? "M" : "A", userVO.getId());
		accountRepository.save(account);
	}

}
