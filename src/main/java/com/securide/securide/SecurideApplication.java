package com.securide.securide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SecurideApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurideApplication.class, args);
	}

}
