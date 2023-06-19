package com.example.cakeshopapp.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cupcake {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cupcake_id", nullable = false)
    private Long id;
    private String name;
    private String description;
    @OneToMany
    private List<Flavors> flavors;
    private int priceFor10Servings;

}