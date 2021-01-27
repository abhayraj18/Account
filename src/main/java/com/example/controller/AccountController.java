package com.example.controller;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.form.AccountForm;
import com.example.service.AccountService;
import com.example.service.RestAPIWebClient;
import com.example.vo.UserVO;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/account")
public class AccountController {

	@Autowired
	private RestAPIWebClient webClient;

	@Autowired
	private AccountService accountService;

	@GetMapping("/get/{userId}")
	public Mono<UserVO> getUser(@Context HttpServletRequest request, @Context HttpServletResponse response,
			@PathVariable("userId") Integer userId)
			throws EntityNotFoundException {
		String authToken = request.getHeader("Authorization");
		return webClient.getUserByID(userId, authToken);
	}

	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<Object> create(@RequestBody @Validated AccountForm form, Errors result) {
		if (result.hasErrors()) {
			return new ResponseEntity<Object>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}

		accountService.createAccount(form);
		return new ResponseEntity<Object>("Account created successfully", HttpStatus.OK);
	}
}
