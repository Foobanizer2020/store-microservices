package com.microservices.payment.service;

import java.util.List;

import com.microservices.payment.entity.Card;

public interface CardService {
	public List<Card> getAll();
	public List<Card> getAllByCustomerId(Long id);
	public Card read(Long id);
	public Card create(Card card);
	public Card update(Card card);
	public Card delete(Card card);
	public Card updateBalance(Long id, Long quantity);
}
