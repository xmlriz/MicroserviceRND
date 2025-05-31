package com.capgemini.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author RizwanAli
 * @version 1.0
 * @date 2023-10-05
 * @description This class represents the details of a transaction.
 */

@Entity
@Table(name = "transaction_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long transactionId;
	
	@Column(name = "order_id", nullable = false)
	private long orderId;
	
	@Column(name = "payment_mode", nullable = false)
	private String paymentMode;
	
	@Column(name = "payment_status", nullable = false)
	private String paymentStatus;
	
	@Column(name = "transaction_date", nullable = false)
	private Instant transactionDate;
	
	@Column(name = "reference_id", nullable = false)
	private String referenceId;
	
	@Column(name = "amount", nullable = false)
	private long amount;
	
}
