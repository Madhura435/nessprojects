package com.madhura.demoofoauth;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoofoauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoofoauthApplication.class, args);

	}

	
	@GetMapping("/")
	public String googlewelcome() {
	//System.out.println("username: "+principal.getName());
		return "madhura";
	}
	@GetMapping("/user")
	public Principal welcome(Principal p) {
	System.out.println("username: "+p.getName());
		return p;
	}
	
	
}
