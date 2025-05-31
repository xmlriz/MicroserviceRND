package com.capgemini.main.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.main.models.ProductRequest;
import com.capgemini.main.models.ProductResponse;
import com.capgemini.main.services.ProductService;
/**
 * @author Rizwan Ali
 * @since 2023-10-01
 * @apiNote This is the ProductController class 
 * which handles HTTP requests related to products.
 */


@RestController
@RequestMapping("/product")
public class ProductController {

	ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@PostMapping("/")
	public ResponseEntity<Long> addProduct(@RequestBody ProductRequest productRequest){
		long pid = productService.saveProduct(productRequest);
		return new ResponseEntity<>(pid,HttpStatus.CREATED);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable long id){
		ProductResponse productResponse= productService.getProductById(id);
		return new ResponseEntity<ProductResponse>(productResponse,HttpStatus.FOUND);
	}
	@PutMapping("/reduceQuantity/{id}")
	public ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId,
			@RequestParam long productQuantity){
		productService.productReduceQuantity(productId,productQuantity);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}