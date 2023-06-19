package com.example.cakeshopapp.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.List;
import java.util.logging.Filter;

@Entity
@Data
public class Cake {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cake_id", nullable = false)
    private Long cake_id;
    private String name;
    private String description;
    @OneToMany
    private List<Flavor> flavors;
    private int priceFor10Servings;

    public Cake(String name, String description, List<Flavor> flavors, int priceFor10Servings) {
        this.name = name;
        this.description = description;
        this.flavors = flavors;
        this.priceFor10Servings = priceFor10Servings;
    }
}
