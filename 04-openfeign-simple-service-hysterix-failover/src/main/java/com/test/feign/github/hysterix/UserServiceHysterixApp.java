package com.test.feign.github.hysterix;

import java.util.List;

import feign.Logger;
import feign.gson.GsonDecoder;
import feign.hystrix.HystrixFeign;

public final class UserServiceHysterixApp {
	
	public static void main(String... args) {
		UserService service = HystrixFeign.builder()
				.decoder(new GsonDecoder())
                .logger(new Logger.JavaLogger().appendToFile("simpleApp.log"))
                .logLevel(Logger.Level.FULL)
				.target(UserService.class, "https://jsonplaceholder.typicode.com", new UserServiceFallbackFactory());

		System.out.println(service);
		fetch(service, false, 10);
	}
	
	private static void fetch(UserService service, boolean details, int count) {
		for (int i = 0; i <= count; i++) {
			List<User> users = service.users();
			System.out.println("Got Users: "+ users.size());
			if(details) {
				for (User user : users) {
					System.out.println(user);
				}
			}
		}
	}
}