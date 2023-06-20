package com.example.cakeshopapp.Repository;

import com.example.cakeshopapp.Models.Cake;
import com.example.cakeshopapp.Models.Flavor;
import com.example.cakeshopapp.Models.Flavor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlavorsRepository extends JpaRepository<Flavor, Long> {
}

