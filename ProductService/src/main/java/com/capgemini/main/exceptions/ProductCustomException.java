package com.capgemini.main.exceptions;

import lombok.Data;

@Data
public class ProductCustomException extends RuntimeException{
	
	private static final long serialVersionUID = -5251628164495671986L;
	private String errorCode;
	public ProductCustomException(String message ,String errorCode) {
		super(message);
		this.errorCode=errorCode;
	}
}
