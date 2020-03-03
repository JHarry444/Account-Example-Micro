package com.qa.account.example.service;

import java.util.List;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.qa.account.example.persistence.domain.Account;
import com.qa.account.example.persistence.repo.AccountRepo;

@Service
public class AccountService {

	private AccountRepo repo;
	private LogService log;
	private RestTemplate rest;

	@Value("${services.locations.number-generator}")
	private String numGenURL;

	@Value("${services.locations.prize-generator}")
	private String prizeGenURL;

	public AccountService(AccountRepo repo, LogService log, RestTemplateBuilder restBuilder) {
		super();
		this.repo = repo;
		this.log = log;
		this.rest = restBuilder.build();
	}

	public ResponseEntity<List<Account>> getAccounts() {
		log.log("GET all Accounts");
		return ResponseEntity.ok(repo.findAll());
	}

	public ResponseEntity<Account> getAccount(Long id) {
		try {
			log.log("GET Account with id: " + id);
			Account found = repo.findById(id).orElseThrow(() -> new AccountNotFoundException(id.toString()));
			return ResponseEntity.ok(found);
		} catch (AccountNotFoundException anfe) {
			return ResponseEntity.notFound().build();
		}

	}

	public ResponseEntity<Account> addAccount(Account account) {
		account.setAccountNumber(this.rest.getForObject(numGenURL, String.class));
		account.setPrize(this.rest.getForObject(prizeGenURL + account.getAccountNumber(), Double.class));
		log.log("POST " + account);
		return ResponseEntity.ok(this.repo.save(account));
	}

	public ResponseEntity<Object> deleteAccount(Long id) {
		log.log("DELETE Account with id: " + id);
		if (accountExists(id)) {
			repo.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	private boolean accountExists(Long id) {
		return repo.findById(id).isPresent();
	}

	public ResponseEntity<Object> updateAccount(Account account, Long id) {
		if (accountExists(id)) {
			Account toUpdate = this.repo.findById(id).get();
			log.log("UPDATE " + toUpdate + "to be " + account);
			toUpdate.setFirstName(account.getFirstName());
			toUpdate.setLastName(account.getLastName());
			repo.save(account);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
