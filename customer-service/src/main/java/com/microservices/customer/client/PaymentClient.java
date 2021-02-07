 package com.microservices.customer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.microservices.customer.model.*;


@FeignClient(name="payment-service")
@RequestMapping(value = "/cards")
public interface PaymentClient {
	@GetMapping(value = "/{id}")
	public ResponseEntity<Card> getCard(@PathVariable("id") Long id);
	
}
