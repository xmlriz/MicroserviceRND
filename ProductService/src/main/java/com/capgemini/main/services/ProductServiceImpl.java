package com.capgemini.main.services;

import org.springframework.stereotype.Service;

import com.capgemini.main.entity.Product;
import com.capgemini.main.exceptions.ProductCustomException;
import com.capgemini.main.models.ProductRequest;
import com.capgemini.main.models.ProductResponse;
import com.capgemini.main.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{

	ProductRepository productRepository;
	public ProductServiceImpl(ProductRepository productRepository) {
		
		this.productRepository = productRepository;
	}

	@Override
	public long saveProduct(ProductRequest productRequest) {
		log.info("product save request : "+productRequest);
		Product product = Product.builder()
				.ProductName(productRequest.name())
				.price(productRequest.price())
				.quantity(productRequest.quantity())
				.build();
		productRepository.save(product);
		log.info("product saved with id {}: ",product.getProductId());
		
		return product.getProductId();
	}

	@Override
	public ProductResponse getProductById(long productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(()->new ProductCustomException("Product not found","PRODUCT_NOT_FOUND"));
		ProductResponse productResponse = ProductResponse.builder()
				.productId(productId)
				.ProductName(product.getProductName())
				.quantity(product.getQuantity())
				.price(product.getPrice())
				.build();		
		return productResponse;
	}
	
	@Override
	public void productReduceQuantity(long productId, long productQuantity) {
		log.info("Reduce request for product {} for reduce quantity{} ",productId,productQuantity);

		Product product = productRepository.findById(productId).orElseThrow(
				()-> new ProductCustomException("Product not found ", "404 Not Found"));
		
		if(product.getQuantity()<productQuantity)
			throw new ProductCustomException("Product does not have sufficient quantity", "INSUFFICIENT_PRODUCT");
		
		product.setQuantity(product.getQuantity()-productQuantity);
		productRepository.save(product);
		log.info("Product Quantity updated successfully");	
	}

}
