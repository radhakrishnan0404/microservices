package com.zygotecorp.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
public class ConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}
	
	@RefreshScope
	@RestController
	class MessageRestController {
	 
	    @Value("${msg:Hello world - Config Server is not working..pelase check}")
	    private String msg;
	 
	    @RequestMapping("/msg")
	    String getMsg() {
	        return this.msg;
	    }
	}

}
