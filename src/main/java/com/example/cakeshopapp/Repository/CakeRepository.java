package com.example.cakeshopapp.Repository;

import com.example.cakeshopapp.Models.Cake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CakeRepository extends JpaRepository<Cake, Long> {
    Cake findByName(String name);
    void deleteByName(String name);
}
