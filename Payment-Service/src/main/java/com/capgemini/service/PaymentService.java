package com.capgemini.service;

import com.capgemini.model.PaymentRequest;

/**
 * @author RizwanAli
 * Service interface for payment operations.
 */
public interface PaymentService {

	long processPayment(PaymentRequest paymentRequest);

}
