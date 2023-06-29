package com.example.cakeshopapp.Models.exceptions;


public class InvalidUserCredentialsException extends RuntimeException {

    public InvalidUserCredentialsException() {
        super("Invalid user credentials!");
    }
}
