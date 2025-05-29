package com.capgemini.external.decoder;

import java.io.IOException;

import com.capgemini.exceptions.OrderServiceCustomException;
import com.capgemini.external.response.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomErrorDecoder implements ErrorDecoder{

	@Override
	public Exception decode(String methodKey, Response response) {
		
		log.info("::{}",response.request().url());
		log.info("::{}",response.request().headers());
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			ErrorResponse errorResponse = objectMapper.readValue(response.body().asInputStream(),ErrorResponse.class);
			
			throw new OrderServiceCustomException(errorResponse.getErrorMessage(), errorResponse.getErrorCode());
		} 
		catch (IOException e) {
			throw new OrderServiceCustomException(e.getMessage(),"500");
		}
	}
}
