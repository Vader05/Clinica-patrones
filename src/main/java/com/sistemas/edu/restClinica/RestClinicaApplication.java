package com.sistemas.edu.restClinica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class RestClinicaApplication {
	

	private static BCryptPasswordEncoder bc= new BCryptPasswordEncoder();

	public static void main(String[] args) {
		SpringApplication.run(RestClinicaApplication.class, args);
		/*
		String contra="12345";
		for(int i=0; i<10; i++) {
			String pass=bc.encode(contra);
			System.out.println(pass);
		}
		*/
		
	}
	
}
