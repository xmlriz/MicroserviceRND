package com.capgemini.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
