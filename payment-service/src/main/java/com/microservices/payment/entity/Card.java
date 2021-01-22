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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "El número de tarjeta no puede ser vacío")
	@Size(min = 16, max = 16, message = "El número de tarjeta debe ser de tamaño 16")
	@Column(length = 16, unique = true, nullable = false)
	private String number;
	
	@NotNull(message = "La fecha de expiración no puede ser vacía")
	@Column(name = "exp_date", nullable = false)
	private Date expDate;
	
	@NotEmpty(message = "El cvv no puede ser vacío")
	@Column(nullable = false)
	private String cvv;
	
	private Long balance;
	
	@Valid
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
	private Bank bank;
	
	@NotNull(message = "El id del cliente no puede ser vacío")
	@Column(name = "customer", nullable = false)
	private Long customerId;
}
