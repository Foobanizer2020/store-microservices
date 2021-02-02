package com.microservices.customer.model;

import java.util.Date;

import lombok.Data;

@Data
public class Card {

	private Long id;
	private String number;
	private Date expDate;
	private String cvv;
	private Long balance;
	private Bank bank;
	private Long customerId;
}
