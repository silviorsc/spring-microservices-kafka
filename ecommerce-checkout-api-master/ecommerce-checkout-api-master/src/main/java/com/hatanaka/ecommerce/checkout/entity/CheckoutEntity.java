package com.hatanaka.ecommerce.checkout.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Builder
@Data
@Entity
public class CheckoutEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String code;



}
