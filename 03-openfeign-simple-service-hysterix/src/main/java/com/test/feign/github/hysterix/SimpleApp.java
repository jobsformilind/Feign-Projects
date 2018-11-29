package com.test.feign.github.hysterix;

import java.util.List;

import feign.Logger;
import feign.gson.GsonDecoder;
import feign.hystrix.HystrixFeign;

public final class SimpleApp {
	
	public static void main(String... args) {
		GitHub github = HystrixFeign.builder()
				.decoder(new GsonDecoder())
                .logger(new Logger.JavaLogger().appendToFile("simpleApp.log"))
                .logLevel(Logger.Level.FULL)
				.target(GitHub.class, "https://api.github.com", new GitHubFallbackFactory());

		System.out.println(github);
		fetchContributors(github, false, 10);
	}
	
	private static void fetchContributors(GitHub github, boolean details, int count) {
		// Fetch and print a list of the contributors to this library.
		for (int i = 0; i <= count; i++) {
			List<Contributor> contributors = github.contributors("OpenFeign", "feign");
			System.out.println("Got contributors: "+ contributors.size());
			if(details) {
				for (Contributor contributor : contributors) {
					System.out.println(contributor);
				}
			}
		}
	}
}