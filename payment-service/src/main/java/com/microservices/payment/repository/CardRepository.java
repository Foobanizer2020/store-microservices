package com.microservices.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.payment.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
	
}
