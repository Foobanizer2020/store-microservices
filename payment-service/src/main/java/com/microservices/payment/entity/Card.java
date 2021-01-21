package com.microservices.payment.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size( min = 16, max = 16, message = "El tamaño del número debe ser 16")
	@Column(length = 16)
	private Long number;
	
	@Column(name = "exp_date")
	private Date expDate;
	
	private String cvv;
	
	private Long balance;
	
	@Valid
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
	private Bank bank;
	
	@Column(name = "customer")
	private Long customerId;
}
