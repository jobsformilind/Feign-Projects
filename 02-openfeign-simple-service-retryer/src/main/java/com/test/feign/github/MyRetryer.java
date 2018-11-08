package com.test.feign.github;

import feign.RetryableException;
import feign.Retryer;

public class MyRetryer implements Retryer {

	public void continueOrPropagate(RetryableException e) {
		System.out.println("Got retry exception : " + e);
	}

	@Override
	public Retryer clone() {
		System.out.println("Cloning Retryer...");
		return null;
	}

}
