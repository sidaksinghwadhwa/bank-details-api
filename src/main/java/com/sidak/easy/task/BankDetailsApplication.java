package com.sidak.easy.task;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories("com.sidak.easy.task.repo") 
@EntityScan("com.sidak.easy.task.entity")
@SpringBootApplication
public class BankDetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankDetailsApplication.class, args);
	}

}
