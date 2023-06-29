package com.example.cakeshopapp.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;
    private String productName;
    private String flavor;
    private int quantity;
    private int price;

    public Product() {

    }
    public Product(String productName, String flavor, int quantity, int price) {
        this.productName = productName;
        this.flavor = flavor;
        this.quantity=quantity;
        this.price = price;

    }


}
