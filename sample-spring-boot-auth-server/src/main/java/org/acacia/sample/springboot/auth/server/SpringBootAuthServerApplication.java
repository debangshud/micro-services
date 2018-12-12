package org.acacia.sample.springboot.auth.server;

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