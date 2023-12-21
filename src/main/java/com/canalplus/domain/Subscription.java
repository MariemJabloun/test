package com.canalplus.domain;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Table(name="Subscription")
@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "customer"})
public class Subscription implements Serializable{


    @Id
    @GeneratedValue
    @Column(name= "id")
    private Long id;
    @Column(name="name")
    private String subscriptionName;  // CANAL+, canal+ cine series, canal + sport, canal+ friends and family 
    @Column(name = "active", nullable = false)
    private Boolean active;
    @Column(name= "startdate", updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @NotNull
    private Date startDate;
    // More checks can be done on expiry date and active. Expiry date should be after creation date....
    @Column(name= "expirydate", nullable = false)
    private Date expiryDate;
    @Column(name = "canceldate", nullable = true)
    private Date cancelDate;
    private String cancelReason;
    private String description;
    private String price;
    private String currency;
    private String recurrency; // Monthly, weekly
    /* Model can be enrished with trial period, payment, invoices, ...*/
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fk_customer", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;


    
}
