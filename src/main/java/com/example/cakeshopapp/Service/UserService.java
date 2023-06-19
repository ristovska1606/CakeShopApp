package com.example.cakeshopapp.Service;

import com.example.cakeshopapp.Models.User;

public interface UserService {
    User create();
    User findById(Long id);
}