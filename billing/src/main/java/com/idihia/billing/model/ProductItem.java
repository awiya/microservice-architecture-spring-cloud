package com.idihia.billing.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.idihia.billing.notmodel.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class ProductItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private String name;
    @ManyToOne
    @JsonIgnore
    private Bill bill;
    private int quantity;
    private double price;
    private double discount;
    @Transient
    private Product product;

}
