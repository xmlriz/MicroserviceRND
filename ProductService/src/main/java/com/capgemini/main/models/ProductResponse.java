package com.capgemini.main.models;

import lombok.Builder;

@Builder
public record ProductResponse(
		String ProductName,
		long productId,
		long quantity,
		long price
		) {}
