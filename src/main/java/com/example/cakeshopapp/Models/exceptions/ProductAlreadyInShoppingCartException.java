package com.example.cakeshopapp.Models.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class ProductAlreadyInShoppingCartException extends RuntimeException{

    public ProductAlreadyInShoppingCartException() {
        super("The product is already in shopping cart.");
    }
}
