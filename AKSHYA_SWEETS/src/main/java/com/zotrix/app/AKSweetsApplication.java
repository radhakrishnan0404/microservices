																																																																																																																																																																																																																																			package com.zotrix.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.zotrix.controller")
public class AKSweetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AKSweetsApplication.class, args);
	}

}
