package com.capgemini.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entity.TransactionDetails;
import com.capgemini.model.PaymentRequest;
import com.capgemini.repository.TransactionRepostory;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class PayementServiceImpl implements PaymentService {

	@Autowired
	TransactionRepostory transactionRepostory;

	@Override
	public long processPayment(PaymentRequest paymentRequest) {
		log.info("Processing payment for orderId: {}, amount: {}, paymentMode: {}, referenceId: {}",
				paymentRequest.orderId(), paymentRequest.amount(), paymentRequest.paymentMode(),
				paymentRequest.referenceId());
		TransactionDetails transactionDetails = TransactionDetails.builder()
				.orderId(paymentRequest.orderId())
				.paymentMode(paymentRequest.paymentMode().name())
				.amount(paymentRequest.amount())
				.referenceId(paymentRequest.referenceId())
				.paymentStatus("SUCCESS")
				.transactionDate(Instant.now())
				.build();
		TransactionDetails saveResponse = transactionRepostory.save(transactionDetails);
		log.info("Payment processed successfully with transactionId: {}", saveResponse.getTransactionId());
		return saveResponse.getTransactionId();
	}
}
