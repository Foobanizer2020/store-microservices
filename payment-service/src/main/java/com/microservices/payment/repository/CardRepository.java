package com.microservices.payment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.payment.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {
	public List<Card> findByCustomerId(Long customerId);
}
