package com.example.cakeshopapp.Models.exceptions;

public class ProductDoesntExist extends RuntimeException{
    public ProductDoesntExist() {
        super("Product doesn't exist");
    }
}
