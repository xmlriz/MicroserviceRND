package com.capgemini.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.capgemini.external.decoder.CustomErrorDecoder;

import feign.codec.ErrorDecoder;

/**
 * 
 * this is feign client config class 
 * which provide the customDecoder.
 * 
 * We have already implemented CustomErrorDecorder
 * whenever ErrorDecoder trigger Spring will provide 
 * our own CustomDecoder because of this class .  
 */


@Configuration
public class DecoderConfig {
	@Bean
	ErrorDecoder errorDecorder(){
		return new CustomErrorDecoder();
	}
}
