package com.zygotecorp.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsNotifysrvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsNotifysrvcApplication.class, args);
	}

}
