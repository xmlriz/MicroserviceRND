package com.capgemini.main.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.capgemini.main.models.ErrorResponse;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(ProductCustomException.class)
	public ResponseEntity<ErrorResponse> handleProductServiceException(ProductCustomException ex){		

		return new ResponseEntity<>(ErrorResponse.builder()
				.errorMessage(ex.getMessage())
				.errorCode(ex.getErrorCode())
				.build(),HttpStatus.NOT_FOUND);
	}
}
