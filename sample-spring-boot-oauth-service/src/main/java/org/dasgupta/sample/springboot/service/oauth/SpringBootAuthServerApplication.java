package org.dasgupta.sample.springboot.service.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@EnableAuthorizationServer
@SpringBootApplication
public class SpringBootAuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAuthServerApplication.class, args);
	}
}