package com.microservices.payment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.microservices.payment.entity.Card;
import com.microservices.payment.repository.CardRepository;

public class CardServiceImpl implements CardService {

	@Autowired
	private CardRepository cardRepository;
	
	@Override
	public List<Card> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Card getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Card create(Card card) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Card update(Card card) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Card delete(Card card) {
		// TODO Auto-generated method stub
		return null;
	}

}
