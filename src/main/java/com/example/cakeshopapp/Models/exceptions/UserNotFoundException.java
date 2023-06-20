package com.example.cakeshopapp.Models.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("User not found!");
    }
}
