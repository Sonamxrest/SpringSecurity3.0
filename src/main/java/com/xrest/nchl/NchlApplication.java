package com.xrest.nchl;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableAutoConfiguration
public class NchlApplication {

	public static void main(String[] args) {
		SpringApplication.run(NchlApplication.class, args);
	}

}
