package com.exalt.villa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories("com.exalt.villa.repository")
@ComponentScan("com.exalt.villa.*")
public class VillaApplication {

	public static void main(String[] args) {
		SpringApplication.run(VillaApplication.class, args);
	}

}
