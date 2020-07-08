package com.zygotecorp.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Controller
@SpringBootApplication
@EnableHystrix
@RestController
public class MsHealthcheckApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsHealthcheckApplication.class, args);
	}
	
		@RequestMapping(value = "/")
	   @HystrixCommand(fallbackMethod = "fallback_hello")
	   public String hello() throws InterruptedException {
	      Thread.sleep(3000);
	      return "Welcome Hystrix";
	   }
	   private String fallback_hello() {
	      return "Request fails. It takes long time to response";
	   }

}
