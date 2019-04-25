package com.example.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class }, webEnvironment = WebEnvironment.DEFINED_PORT)
public class ApplicationTests {

	private static final String API_ROOT = "http://localhost:8081/api/users";

	@Test
	public void whenGetAll_thenOK() {
		Response response = RestAssured.get(API_ROOT);
		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	}

	@Test
	public void whenGetBooksByTitle_thenOK() {
		User user = new User("milind");
		createAsUri(user);
		Response response = RestAssured.get(API_ROOT + "/name/" + user.getName());
		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
		assertTrue(response.as(List.class).size() > 0);
	}

	private String createAsUri(User user) {
		Response response = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(user).post(API_ROOT);
		return API_ROOT + "/" + response.jsonPath().get("id");
	}
	
	
	@Test
	public void whenGetNotExistUserByName_thenNotFound() {
		Response response = RestAssured.get(API_ROOT + "/name/patil");	     
	    assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
	}	
}
