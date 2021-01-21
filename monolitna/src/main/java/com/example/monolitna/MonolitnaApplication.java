package com.example.monolitna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableJpaRepositories
public class MonolitnaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonolitnaApplication.class, args);
	}

}
