package com.example.cakeshopapp.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flavors {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name="cake_id", nullable=true)
    private Cake cakes;
    @ManyToOne
    @JoinColumn(name="cake_id", nullable=true)
    private Cupcake cupcakes;
}
