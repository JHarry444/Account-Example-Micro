package com.qa.account.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;

@SpringBootApplication
public class AccountExampleApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AccountExampleApplication.class, args);
	}

}
