package com.example.cakeshopapp.Repository;

import com.example.cakeshopapp.Models.Cake;
import com.example.cakeshopapp.Models.Cart;
import com.example.cakeshopapp.Models.User;
import com.example.cakeshopapp.Models.enums.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserAndStatus(User user, CartStatus status);

}
