package com.qa.account.gen.prize.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PrizeGenService {

	public Double genPrize(String accountNum) {
		Double prize = null;
		if (accountNum.toLowerCase().startsWith("a")) {
			prize = 0.0;
		} else if (accountNum.toLowerCase().startsWith("b")) {
			if (accountNum.length() == 7) {
				prize = 50.0;
			} else if (accountNum.length() == 9) {
				prize = 500.0;
			} else if (accountNum.length() == 10) {
				prize = 5000.0;
			}
		} else if (accountNum.toLowerCase().startsWith("c")) {
			if (accountNum.length() == 7) {
				prize = 100.0;
			} else if (accountNum.length() == 9) {
				prize = 750.0;
			} else if (accountNum.length() == 10) {
				prize = 10000.0;
			}
		}
		return prize;
	}
}
