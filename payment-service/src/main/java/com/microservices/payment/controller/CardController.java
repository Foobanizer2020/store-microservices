package com.microservices.payment.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.payment.entity.Card;
import com.microservices.payment.service.CardService;

@RestController
@RequestMapping(value = "/cards")
public class CardController {
	@Autowired
	private CardService cardService;
	
	@GetMapping
	public ResponseEntity<List<Card>> listCard(@RequestParam(name = "customerId", required = false) Long customerId) {
		List<Card> cards;
		if (customerId == null) {
			cards = cardService.getAll();
			if (cards.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
		} else {
			cards = cardService.getAllByCustomerId(customerId);
			if (cards.isEmpty()) {
				return ResponseEntity.notFound().build();
			}
		}
		return ResponseEntity.ok(cards);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Card> getCard(@PathVariable("id") Long id) {
		Card card = cardService.read(id);
		if(card == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(card);
	}
	
	@PostMapping
	public ResponseEntity<Card> createCard(@Valid @RequestBody Card card) {
		Card newCard = cardService.create(card);
		return ResponseEntity.status(HttpStatus.CREATED).body(newCard);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Card> updateCard(@PathVariable("id") Long id, @Valid @RequestBody Card card) {
		card.setId(id);
		Card cardDB = cardService.update(card);
		if (cardDB == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cardDB);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Card> deleteCard(@PathVariable("id") Long id) {
		Card cardDB = cardService.delete(Card.builder().id(id).build());
		if(cardDB == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(cardDB);
	}
	
	@PutMapping(value = "/{id}/balance")
	public ResponseEntity<Card> updateBalance(@PathVariable("id") Long id, @RequestParam(name = "quantity", required = true) Long quantity) {
		Card card = cardService.updateBalance(id, quantity);
		if (card == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(card);
	}
} 
