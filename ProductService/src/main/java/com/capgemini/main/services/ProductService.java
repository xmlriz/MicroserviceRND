package com.capgemini.main.services;

import com.capgemini.main.models.ProductRequest;
import com.capgemini.main.models.ProductResponse;

public interface ProductService {
	
	public long saveProduct(ProductRequest productRequest);
	public ProductResponse getProductById(long productId);
	public void productReduceQuantity(long productId, long productQuantity);

}
