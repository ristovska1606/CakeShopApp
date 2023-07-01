package com.example.cakeshopapp.Service;

import com.example.cakeshopapp.Models.Cart;
import com.example.cakeshopapp.Models.Order;
import com.example.cakeshopapp.Models.Product;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> listAll();
    Order create(Cart cart, String cartNumber, String dateAndTime, String note);
    void deleteByCartId(Long orderId);
    Optional<Order> findByCartId(Long cartId);
}
