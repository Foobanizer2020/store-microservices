package com.microservices.shopping.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.microservices.shopping.model.*;

@FeignClient(name = "payment-service", fallback = CardHystrixFallbackFactory.class)
public interface CardClient {
	@PutMapping(value = "/cards/{id}/balance")
	public ResponseEntity<Card> updateBalance(@PathVariable("id") Long id, @RequestParam(name = "quantity", required = true) Long quantity);
	
	@GetMapping(value = "/cards/{id}")
	public ResponseEntity<Card> getCard(@PathVariable("id") Long id);
}
