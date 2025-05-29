package com.capgemini.models;

import lombok.Builder;

@Builder
public record OrderRequest(
		
		 long productId,
		 long amount,
		 long quantity,
		 PaymentMode paymentMode
		
		) {}
