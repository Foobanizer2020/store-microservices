package com.microservices.shopping.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.microservices.shopping.model.Customer;

import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Invoice {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_invoice")
    private String numberInvoice;

    private String description;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @Valid
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    private List<InvoiceItem> items;

    private String state;

    @Transient
    private Customer customer;
    
    @Column(name = "payment_method", nullable = false)
    @Valid
    @NotNull(message = "El método de pago no puede ser vacío")
    private String paymentMethod;

    @Column(name = "card_id", nullable = true)
    private Long cardId;
    
    @Column(name = "card_last_digits", nullable = true)
    private String cardLastDigits;
    
    @Transient
    private Long subTotal;
    
    public Invoice(){
        items = new ArrayList<>();
    }

    public Long getSubTotal() {
    	Long total = (long) 0;
    	for(InvoiceItem i : items) {
    		total += i.getSubTotal().longValue();
    	}
    	return total;
    }
    
    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
    }

}
