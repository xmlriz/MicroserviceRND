package com.capgemini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.model.PaymentRequest;
import com.capgemini.service.PaymentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/payment")
@AllArgsConstructor
public class PayementController {

	@Autowired
	private PaymentService paymentService;
	
	@GetMapping("/")
	public String ping() {
		return "Payament service up and running";
	}
	
	@PostMapping("/")
	public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest) {
		return ResponseEntity.status(HttpStatus.OK).body(paymentService.processPayment(paymentRequest));
	}
}