package com.qa.account.gen.prize.controller;

import javax.websocket.server.PathParam;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.account.gen.prize.service.PrizeGenService;

@RestController
@RequestMapping("/prize")
public class PrizeGenController {

	private PrizeGenService service;

	public PrizeGenController(PrizeGenService service) {
		super();
		this.service = service;
	}

	@GetMapping("/generate")
	public ResponseEntity<Double> generatePrize(@PathParam("accNumber") String accNumber) {
		Double prize =  this.service.genPrize(accNumber);
		if (prize == null) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.ok(prize);
		}
	}
}