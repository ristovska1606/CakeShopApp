package com.example.cakeshopapp.Repository;

import com.example.cakeshopapp.Models.Cake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Cake, Long> {
}
