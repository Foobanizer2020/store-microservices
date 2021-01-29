package com.microservices.payment.service;

import java.util.List;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.microservices.payment.entity.Card;
import com.microservices.payment.repository.CardRepository;
import com.microservices.payment.util.AESUtil;

@Service
public class CardServiceImpl implements CardService {
	
	@Value("${aes.password}")
	private String aesPassword;
	
	@Value("${aes.salt}")
	private String aesSalt;
	
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
		String cipherCvv = this.encryptCvv(card.getCvv());
		if (cipherCvv != null) {
			card.setCvv(this.encryptCvv(card.getCvv()));
			card = cardRepository.save(card);
		} else {
			card = null;
		}
		return card;
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
		try {
			SecretKey key = AESUtil.getKeyFrompassword(this.aesPassword, this.aesSalt);
			IvParameterSpec iv = AESUtil.generateIv();
			String cipherCvv = AESUtil.encrypt("AES/CBC/PKCS5Padding", cvv, key, iv);
			return cipherCvv;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Card updateBalance(Long id, Long quantity) {
		Card cardDB = this.read(id);
		if (cardDB != null) {
			cardDB.setBalance(cardDB.getBalance() + quantity);
			cardDB = cardRepository.save(cardDB);
		}
		return cardDB;
	}
	
}
