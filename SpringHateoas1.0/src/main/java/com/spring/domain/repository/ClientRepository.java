package com.spring.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.domain.entity.Client;


public interface ClientRepository extends JpaRepository<Client, Long>{

}
