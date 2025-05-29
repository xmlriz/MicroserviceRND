package com.capgemini.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.models.OrderRequest;
import com.capgemini.models.OrderResponse;
import com.capgemini.services.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	OrderService orderService;
	
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderResponse> getOrderById(@PathVariable long id) {
		return ResponseEntity.status(HttpStatus.FOUND)
				.body(orderService.getOrderByOrderId(id));
	}
	
	@GetMapping("/")
	public String ping() {
		return "<h1> ORDER-SERVICE </h1>";
	}
	
	@PostMapping("/")
	public ResponseEntity<Long> orderPlace(@RequestBody OrderRequest orderRequest) {
		long orderID = orderService.orderCreated(orderRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(orderID);
	}
}
