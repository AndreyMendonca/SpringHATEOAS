package com.spring.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.domain.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
