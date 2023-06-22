package com.example.cakeshopapp.Models.exceptions;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException() {
        super("User already exists.");
    }
}
