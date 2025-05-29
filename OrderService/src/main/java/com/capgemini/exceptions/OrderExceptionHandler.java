package com.capgemini.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.capgemini.models.OrderErrorResponse;

@ControllerAdvice
public class OrderExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(OrderServiceCustomException.class)
    public ResponseEntity<OrderErrorResponse> orderExceptionHandler(OrderServiceCustomException ex){
    	return ResponseEntity.status(HttpStatus.NOT_FOUND)
    			.body(OrderErrorResponse.builder()
    					.errorCode(ex.getErrorCode())
    					.message(ex.getMessage())
    					.build());
    }
}
