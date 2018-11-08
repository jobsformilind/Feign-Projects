package com.test.feign.github.hysterix;

import feign.Logger;
import feign.gson.GsonDecoder;
import feign.hystrix.FallbackFactory;
import feign.hystrix.HystrixFeign;

public class GitHubFallbackFactory implements FallbackFactory<GitHub>{

	@Override
	public GitHub create(Throwable ex) {
		System.out.println("Invoking fallback since we got exception: " + ex);
		return HystrixFeign.builder()
				.decoder(new GsonDecoder())
                .logger(new Logger.JavaLogger().appendToFile("simpleApp-fallback.log"))
                .logLevel(Logger.Level.FULL)
				.target(GitHub.class, "https://api.github.com");
	}

}
