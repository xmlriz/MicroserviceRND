package com.capgemini.models;

import lombok.Builder;

@Builder
public record OrderResponse(
		
		 long orderId,
		 long productId,
		 long amount,
		 long quantity,
		 PaymentMode paymentMode
		
		) {}
