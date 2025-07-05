package com.edoardoconti.kmz_backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class KmzBackendApplication {

	@Value("${spring.application.name}")
	private String appName;

	public static void main(String[] args) {
		var ctx = SpringApplication.run(KmzBackendApplication.class, args);
		var app = ctx.getBean(KmzBackendApplication.class);
		Environment env = ctx.getEnvironment();
		System.out.println(app.appName + " -  Application started successfully in "
				+ env.getProperty("spring.profiles.active") + " profile");
	}

}
