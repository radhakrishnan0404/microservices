package com.example.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class AKSweetsController {
	
	@RequestMapping("/sweets")
	public String getAllSweets() {
		return "Hai";
	}
	
}
