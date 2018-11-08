package com.test.feign.github.retryer;

import java.util.List;

import feign.Feign;
import feign.Logger;
import feign.Retryer;
import feign.gson.GsonDecoder;

public final class SimpleApp {
	public static void main(String... args) {
		GitHub github = Feign.builder()
				.decoder(new GsonDecoder())
                .logger(new Logger.JavaLogger().appendToFile("simpleApp.log"))
                .logLevel(Logger.Level.FULL)
                //Added Retryer, you can customized Retryer
                .retryer(new Retryer.Default())
				.target(GitHub.class, "https://api.github.com");

		// Fetch and print a list of the contributors to this library.
		List<Contributor> contributors = github.contributors("OpenFeign", "feign");
		for (Contributor contributor : contributors) {
			System.out.println(contributor);
		}
	}
}