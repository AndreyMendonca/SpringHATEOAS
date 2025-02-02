package com.spring.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.domain.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
