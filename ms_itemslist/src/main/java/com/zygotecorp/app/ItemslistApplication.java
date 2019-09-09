package com.zygotecorp.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
public class ItemslistApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemslistApplication.class, args);
	}

}
