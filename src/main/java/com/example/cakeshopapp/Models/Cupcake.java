package com.example.cakeshopapp.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
public class Cupcake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cupcake_id", nullable = false)
    private Long id;
    private String name;
    private String description;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Flavor> flavors;
    private int priceFor10Servings;

    public Cupcake(String name, String description, List<Flavor> flavors, int priceFor10Servings) {
        this.name = name;
        this.description = description;
        this.flavors = flavors;
        this.priceFor10Servings = priceFor10Servings;
    }

    public Cupcake() {

    }
}