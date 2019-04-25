package com.example.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@EnableJpaRepositories("com.example")
@EntityScan("com.example")
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@ControllerAdvice
	public class RestExceptionHandler extends ResponseEntityExceptionHandler {
		@ExceptionHandler({ Exception.class })
		public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
			return handleExceptionInternal(ex, "Exception occured while seraching user", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
		}
	}
}
