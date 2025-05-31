package com.capgemini.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entity.Order;
import com.capgemini.exceptions.OrderServiceCustomException;
import com.capgemini.external.client.PaymentService;
import com.capgemini.external.client.ProductService;
import com.capgemini.external.model.PaymentRequest;
import com.capgemini.models.OrderRequest;
import com.capgemini.models.OrderResponse;
import com.capgemini.repository.OrderRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private PaymentService paymentService;

	
	/**
	 * Order entity -> save the data with status Oder created
	 * call product service -> block the product(reduce the quantity)
	 * payment service-> payment success mark the order success , completed , cancel 
	 */
	@Override
	public long orderCreated(OrderRequest orderRequest) {
		
		log.info("Placing order request {}: ",orderRequest);
		
		productService.reduceQuantity(orderRequest.productId(), orderRequest.quantity());
		
		log.info("Creating Order with Status CREAETED");
		Order order = Order.builder()
				.orderStatus("CREATED")
				.productId(orderRequest.productId())
				.amount(orderRequest.amount())
				.paymentMode(orderRequest.paymentMode())
				.orderDate(Instant.now())
				.quantity(orderRequest.quantity())
				.build();
		order = orderRepository.save(order);
		
		log.info("Calling payment service to complete the payement process");
		
		PaymentRequest paymentRequest = PaymentRequest.builder()
				.orderId(order.getId())
				.amount(orderRequest.amount())
				.paymentMode(orderRequest.paymentMode())
				.build();
		String orderStatus=null;
		
		try {
			paymentService.doPayment(paymentRequest);
			log.info("Payment done successfully for order id : {}",order.getId());
			orderStatus="PLACED";
			log.info("Updating order status to {}",orderStatus);
		}
		catch (Exception e) {
			log.error("Payment failed for order id : {}", order.getId());
			orderStatus = "FAILED";
			log.error("Updating order status to {}", orderStatus);
		}
		order.setOrderStatus(orderStatus);
		orderRepository.save(order);
		log.info("Order place successfully with order id : {}",order.getId());
		
		return order.getId();
	}

	@Override
	public OrderResponse getOrderByOrderId(long id) {
		log.info("Order search request for order id {}",id);
		Order order=orderRepository.findById(id).orElseThrow(()-> new OrderServiceCustomException("Order not found","NOT_FOUND 404"));
		
		return OrderResponse.builder()
				.orderId(order.getId())
				.productId(order.getProductId())
				.amount(order.getAmount())
				.paymentMode(order.getPaymentMode())
				.quantity(order.getQuantity())
				.build();
	}
}
