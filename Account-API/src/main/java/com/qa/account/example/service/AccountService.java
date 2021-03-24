package com.qa.account.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.qa.account.example.persistence.domain.Account;
import com.qa.account.example.persistence.repo.AccountRepo;
import com.qa.account.example.util.exceptions.AccountNotFoundException;

@Service
public class AccountService {

	private AccountRepo repo;
	private LogService log;
	private RestTemplate restTemplate;

	@Value("${services.locations.number-generator}")
	private String numGenURL;

	@Value("${services.locations.prize-generator}")
	private String prizeGenURL;

	public AccountService(AccountRepo repo, LogService log, RestTemplate restTemplate) {
		super();
		this.repo = repo;
		this.log = log;
		this.restTemplate = restTemplate;
	}

	private String getURL(String serviceURL) {
		return serviceURL.startsWith("http") ? serviceURL : "http://" + serviceURL;
	}

	public List<Account> getAccounts() {
		log.log("GET all Accounts");
		return repo.findAll();
	}

	public Account getAccount(Long id) {
		log.log("GET Account with id: " + id);
		Account found = repo.findById(id).orElseThrow(() -> new AccountNotFoundException(id.toString()));
		return found;
	}

	public Account addAccount(Account account) {
		String accountNumber = this.restTemplate.getForObject(getURL(numGenURL), String.class);
		account.setAccountNumber(accountNumber);
		String prize = this.restTemplate.getForObject(getURL(prizeGenURL + "?accNumber=" + accountNumber),
				String.class);
		account.setPrize(Double.parseDouble(prize));
		log.log("POST " + account);
		return this.repo.save(account);
	}

	public boolean deleteAccount(Long id) {
		log.log("DELETE Account with id: " + id);
		repo.deleteById(id);
		return !this.repo.existsById(id);
	}

	public Account updateAccount(Account account, Long id) {
		Account toUpdate = this.repo.findById(id).orElseThrow(() -> new AccountNotFoundException(id.toString()));
		log.log("UPDATE " + toUpdate + "to be " + account);
		toUpdate.setFirstName(account.getFirstName());
		toUpdate.setLastName(account.getLastName());
		return repo.save(account);
	}

}
