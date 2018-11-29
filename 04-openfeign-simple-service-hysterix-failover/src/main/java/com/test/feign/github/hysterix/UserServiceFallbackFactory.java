package com.test.feign.github.hysterix;

import feign.Logger;
import feign.gson.GsonDecoder;
import feign.hystrix.FallbackFactory;
import feign.hystrix.HystrixFeign;

public class UserServiceFallbackFactory implements FallbackFactory<UserService>{

	@Override
	public UserService create(Throwable ex) {
		System.out.println("Invoking fallback since we got exception: " + ex.getMessage());
		return HystrixFeign.builder()
				.decoder(new GsonDecoder())
                .logger(new Logger.JavaLogger().appendToFile("simpleApp-fallback.log"))
                .logLevel(Logger.Level.FULL)
				.target(UserService.class, "https://jsonplaceholder.typicode.com");
	}

}
