package com.test.spring.circuitbreaker;

import java.net.URI;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
@SuppressWarnings("unchecked")
public class UserService {

	private final RestTemplate restTemplate;

	public UserService(RestTemplate rest) {
		this.restTemplate = rest;
	}

	@HystrixCommand(fallbackMethod = "getUsersFallback")
	public List<User> getUsers() {
		System.out.println("Calling getUsers() method of UserService..");
		URI uri = URI.create("https://jsonplaceholder.typicode.com1/users");
		return this.restTemplate.getForObject(uri, List.class);
	}

	public List<User> getUsersFallback() {
		System.out.println("Calling getUsersFallback() method of UserService..");
		URI uri = URI.create("https://jsonplaceholder.typicode.com/users");
		return this.restTemplate.getForObject(uri, List.class);
	}
}
