package com.capgemini.main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "tbl_Product")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long productId;
	
	@Column(name = "Product_Name")
	private String ProductName;
	
	@Column(name = "PRICE")
	private long price;
	
	@Column(name = "QUANTITY")
	private long quantity;
}
