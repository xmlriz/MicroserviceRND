package com.capgemini.external.model;

import com.capgemini.models.PaymentMode;

import lombok.Builder;

@Builder
public record PaymentRequest(long orderId, 
		PaymentMode paymentMode, long amount, 
		String referenceId) {
}
