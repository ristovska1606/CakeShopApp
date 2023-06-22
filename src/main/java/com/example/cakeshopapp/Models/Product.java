package com.example.cakeshopapp.Models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long productId;
    private String productName;
    private String flavor;
    private int quantity;
    private int price;


    public Product(String productName, String flavor, int quantity, int price) {
        this.productName = productName;
        this.flavor = flavor;
        this.quantity=quantity;
        this.price = price;

    }

    public Product() {

    }
}
