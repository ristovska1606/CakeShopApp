package com.example.cakeshopapp.Models.exceptions;

public class PasswordDoNotMatchException extends RuntimeException {
    public PasswordDoNotMatchException() {
        super("Passwords do not match.");
    }
}
