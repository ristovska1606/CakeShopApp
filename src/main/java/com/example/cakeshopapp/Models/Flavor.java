package com.example.cakeshopapp.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flavor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
//    @ManyToOne
//    @JoinColumn(name="cake_id", nullable=true)
//    private Cake cakes;
//    @ManyToOne
//    @JoinColumn(name="cake_id", nullable=true)
//    private Cupcake cupcakes;


    public Flavor(String name) {
        this.name = name;
    }
}
