package com.capgemini.model;

import lombok.Builder;

@Builder
public record PaymentRequest(long orderId, 
		PaymentMode paymentMode, long amount, 
		String referenceId) {
}
