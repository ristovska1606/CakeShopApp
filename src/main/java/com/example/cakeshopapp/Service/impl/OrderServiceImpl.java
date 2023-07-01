package com.example.cakeshopapp.Service.impl;

import com.example.cakeshopapp.Models.Cart;
import com.example.cakeshopapp.Models.Order;
import com.example.cakeshopapp.Models.enums.OrderStatus;
import com.example.cakeshopapp.Models.exceptions.OrderNotFoundException;
import com.example.cakeshopapp.Repository.CartRepository;
import com.example.cakeshopapp.Repository.OrderRepository;
import com.example.cakeshopapp.Service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public List<Order> listAll() {
        return this.orderRepository.findAll();
    }

    @Override
    public Order create(Cart cart, String cartNumber, String dateAndTime, String address, String note) {
        return this.orderRepository.save(new Order(cart, cartNumber, dateAndTime, address, note));
    }

    @Override
    public void deleteByCartId(Long cartId) {
        this.cartRepository.deleteById(cartId);
        this.orderRepository.deleteById(cartId);
    }

    @Override
    public Optional<Order> findByCartId(Long cartId) {
        return Optional.ofNullable(this.orderRepository.findOrderByCart_Id(cartId));
    }

    @Override
    public Optional<Order> findById(Long id) {
        return this.orderRepository.findById(id);
    }

    @Override
    public void markAsFinished(Long id) {
        Order order = this.orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException());
        order.setStatus(OrderStatus.FINISHED);
        this.orderRepository.save(order);
    }
}
