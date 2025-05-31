package com.capgemini.external.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.capgemini.external.model.PaymentRequest;

@FeignClient(name = "Payment-Service/payment")
public interface PaymentService {
	@PostMapping("/")
	public ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest);
}
