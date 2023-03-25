package com.kiubit.webapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApiApplication {

	public static void main(String[] args) {
		System.setProperty("server.servlet.context-path", "/kiubit/api");
		SpringApplication.run(WebApiApplication.class, args);
	}

}
