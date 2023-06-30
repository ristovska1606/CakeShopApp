package com.example.cakeshopapp.Models;

import com.example.cakeshopapp.Models.enums.CartStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;

    @ManyToOne
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Product> products;

    @Enumerated(EnumType.STRING)
    private CartStatus status;

    public Cart() {
    }

    public Cart(User user) {
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.products = new ArrayList<>();
        this.status = CartStatus.CREATED;
    }




}
