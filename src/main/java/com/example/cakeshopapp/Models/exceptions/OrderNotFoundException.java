package com.example.cakeshopapp.Models.exceptions;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException() {
        super("Order not found!");
    }
}
