package com.test.feign.github.hysterix;

import lombok.Data;

@Data
public class Contributor {
	int id;
	String login;
	int contributions;
	String node_id;
	String type;
	boolean site_admin;
}