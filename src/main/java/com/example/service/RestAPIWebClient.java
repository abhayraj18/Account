/**
 * 
 */
package com.example.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.form.UserForm;
import com.example.vo.UserVO;

import reactor.core.publisher.Mono;

/**
 * @author abhay.jain
 *
 */
@Component
public class RestAPIWebClient {
	private WebClient webClient = WebClient.builder().baseUrl("http://localhost:8761")
			.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

	/**
	 * @param userId
	 * @param authToken
	 * @return
	 */
	public Mono<UserVO> getUserByID(Integer userId, String authToken) {
		return webClient.get().uri("/user/get/" + userId).header("Authorization", authToken).retrieve()
				.onStatus(httpStatus -> HttpStatus.NOT_FOUND.equals(httpStatus),
						clientResponse -> clientResponse.createException())
				.onStatus(httpStatus -> HttpStatus.UNAUTHORIZED.equals(httpStatus),
						clientResponse -> clientResponse.createException())
				.bodyToMono(UserVO.class);
	}

	/**
	 * @param userForm
	 * @return
	 */
	public UserVO createUser(UserForm userForm) {
		return webClient.post().uri("/user/create").bodyValue(userForm).retrieve()
				.onStatus(httpStatus -> HttpStatus.BAD_REQUEST.equals(httpStatus),
						clientResponse -> clientResponse.createException())
				.bodyToFlux(UserVO.class).blockFirst();
	}

}