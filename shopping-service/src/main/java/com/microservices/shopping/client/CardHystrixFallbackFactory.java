package com.microservices.shopping.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.microservices.shopping.model.Card;

@Component
public class CardHystrixFallbackFactory implements CardClient {

	@Override
	public ResponseEntity<Card> updateBalance(Long id, Long quantity) {
		return null;
	}

	@Override
	public ResponseEntity<Card> getCard(Long id) {
		Card card = new Card();
		card.setId(null);
		card.setNumber(null);
		return ResponseEntity.ok(card);
	}
	
}
