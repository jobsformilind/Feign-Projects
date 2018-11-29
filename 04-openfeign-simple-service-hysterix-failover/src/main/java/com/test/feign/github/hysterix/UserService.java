package com.test.feign.github.hysterix;

import java.util.List;

import feign.RequestLine;

public interface UserService {
	@RequestLine("GET /users")
	List<User> users();
}