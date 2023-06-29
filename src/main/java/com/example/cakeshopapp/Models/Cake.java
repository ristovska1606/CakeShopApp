package com.example.cakeshopapp.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;

@Entity
@Data
public class Cake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cake_id", nullable = false)
    private Long cake_id;
    private String name;
    private String description;
    @OneToMany (orphanRemoval = true, cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Flavor> flavors;
    private int priceFor10Servings;

    private String productImage;

    public Cake(String name, String description, List<Flavor> flavors, int priceFor10Servings, String productImage) {
        this.name = name;
        this.description = description;
        this.flavors = new ArrayList<>(flavors);
        this.priceFor10Servings = priceFor10Servings;
        this.productImage = productImage;
    }

    public Cake() {

    }
}
