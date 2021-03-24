package com.qa.account.example.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.account.example.persistence.domain.Account;
import com.qa.account.example.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	private AccountService service;

	public AccountController(AccountService service) {
		this.service = service;
	}

	@PostMapping("/register")
	public ResponseEntity<Account> registerAccount(@RequestBody Account account) {
		Account created =  this.service.addAccount(account);
		return new ResponseEntity<Account>(created, HttpStatus.CREATED);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Account>> getAccounts() {
		return ResponseEntity.ok(this.service.getAccounts());
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Account> getAccount(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.getAccount(id));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
		return this.service.deleteAccount(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PutMapping("/update")
	public ResponseEntity<Account> updateAccount(@RequestBody Account account, @PathParam("id") Long id) {
		Account updated =  this.service.updateAccount(account, id);
		return new ResponseEntity<Account>(updated, HttpStatus.ACCEPTED);
	}
}
