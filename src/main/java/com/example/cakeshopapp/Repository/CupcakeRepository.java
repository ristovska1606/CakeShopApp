package com.example.cakeshopapp.Repository;

import com.example.cakeshopapp.Models.Cake;
import com.example.cakeshopapp.Models.Cupcake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CupcakeRepository extends JpaRepository<Cupcake, Long> {
}

