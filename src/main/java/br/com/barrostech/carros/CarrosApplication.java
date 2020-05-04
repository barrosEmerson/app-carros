package br.com.barrostech.carros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class CarrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarrosApplication.class, args);
		
	}



}
