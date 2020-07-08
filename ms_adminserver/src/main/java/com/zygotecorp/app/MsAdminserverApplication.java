package com.zygotecorp.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@EnableAdminServer
public class MsAdminserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAdminserverApplication.class, args);
	}

}
