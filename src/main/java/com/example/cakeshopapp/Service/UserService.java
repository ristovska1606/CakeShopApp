package com.example.cakeshopapp.Service;

import com.example.cakeshopapp.Models.User;

public interface UserService {
    User create(String firstName, String lastName, String email, String password, String confirmPassword, String phoneNumber);
    User findById(Long id);
    User login(String email, String password);
}