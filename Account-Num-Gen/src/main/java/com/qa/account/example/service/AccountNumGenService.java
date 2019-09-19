package com.qa.account.example.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qa.account.example.util.NumberGenerator;

@Service
public class AccountNumGenService {

	private NumberGenerator numGen;

	public AccountNumGenService(NumberGenerator numGen) {
		this.numGen = numGen;
	}

	public ResponseEntity<String> genNumber() {
		return ResponseEntity.ok(this.numGen.genNumber());
	}

}
