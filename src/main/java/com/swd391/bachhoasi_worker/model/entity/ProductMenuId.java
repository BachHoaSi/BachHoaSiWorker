package com.swd391.bachhoasi_worker.model.entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
@Embeddable
@Data
@Builder
public class ProductMenuId implements Serializable{
    @ManyToOne(targetEntity = Menu.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "MenuId", nullable = false)
    private Menu menu;
    @ManyToOne(targetEntity = Product.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "ProductId", nullable = false)
    private Product product;
}
