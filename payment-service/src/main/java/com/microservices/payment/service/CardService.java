package com.microservices.payment.service;

import java.util.List;

import com.microservices.payment.entity.Card;

public interface CardService {
	public List<Card> getAll();
	public Card getById(Long id);
	public Card create(Card card);
	public Card update(Card card);
	public Card delete(Card card);
}
