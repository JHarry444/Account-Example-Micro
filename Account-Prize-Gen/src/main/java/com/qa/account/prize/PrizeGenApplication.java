package com.qa.account.prize;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PrizeGenApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrizeGenApplication.class, args);
	}

}
