package com.capgemini.services;

import com.capgemini.models.OrderRequest;
import com.capgemini.models.OrderResponse;

public interface OrderService {

	public long orderCreated(OrderRequest orderRequest);

	public OrderResponse getOrderByOrderId(long id);

}
