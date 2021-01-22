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
		return cardRepository.findAll();
	}
	
	@Override
	public List<Card> getAllByCustomerId(Long id) {
		return cardRepository.findByCustomerId(id);
	}

	@Override
	public Card read(Long id) {
		return cardRepository.findById(id).orElse(null);
	}

	@Override
	public Card create(Card card) {
		Card cardDB = cardRepository.findById(card.getId()).orElse(null);
		if (cardDB == null) {
			card.setCvv(this.encryptCvv(card.getCvv()));
			cardDB = cardRepository.save(card);
		}
		return cardDB;
	}

	@Override
	public Card update(Card card) {
		Card cardDB = read(card.getId());
		if (cardDB != null) {
			cardDB = cardRepository.save(card);
		}
		return cardDB;
	}

	@Override
	public Card delete(Card card) {
		Card cardDB = this.read(card.getId());
		if (cardDB != null) {
			cardRepository.delete(cardDB);
		}
		return cardDB;
	}

	/**
	 * TODO: Encriptar el cvv
	 * @param cvv
	 * @return el cvv encriptado
	 */
	private String encryptCvv(String cvv) {
		return cvv;
	}

	@Override
	public Card updateBalance(Long id, Long quantity) {
		Card cardDB = this.read(id);
		if (cardDB != null) {
			cardDB.setBalance(cardDB.getBalance() + quantity);
		}
		return cardDB;
	}
	
}
