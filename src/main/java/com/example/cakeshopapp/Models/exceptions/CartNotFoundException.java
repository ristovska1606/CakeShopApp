package com.example.cakeshopapp.Models.exceptions;

public class CartNotFoundException extends RuntimeException{
    public CartNotFoundException() {
        super("Cart not found!");
    }
}
