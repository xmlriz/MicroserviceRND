package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.entity.TransactionDetails;

@Repository
public interface TransactionRepostory extends JpaRepository<TransactionDetails, Long> {
}
