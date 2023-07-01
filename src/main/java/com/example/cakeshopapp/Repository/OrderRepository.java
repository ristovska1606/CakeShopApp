package com.example.cakeshopapp.Repository;

import com.example.cakeshopapp.Models.Cart;
import com.example.cakeshopapp.Models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
void deleteByCart_Id(Long id);
Order findOrderByCart_Id(Long id);
}
