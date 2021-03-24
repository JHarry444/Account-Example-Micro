package com.qa.account.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.account.example.service.AccountNumGenService;

@RestController
public class NumGenController {

	private AccountNumGenService service;

	public NumGenController(AccountNumGenService service) {
		this.service = service;
	}

	@GetMapping("/")
	public ResponseEntity<String> generateNumber() {
		return this.service.genNumber();
	}

}
