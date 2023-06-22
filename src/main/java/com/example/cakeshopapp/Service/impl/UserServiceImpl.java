package com.example.cakeshopapp.Service.impl;

import com.example.cakeshopapp.Models.Cart;
import com.example.cakeshopapp.Models.User;
import com.example.cakeshopapp.Models.exceptions.*;
import com.example.cakeshopapp.Repository.UserRepository;
import com.example.cakeshopapp.Service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(String firstName, String lastName, String email, String password, String confirmPassword, String phoneNumber) {
        if(!password.equals(confirmPassword))
            throw new PasswordDoNotMatchException();


        if(userRepository.findByEmail(email).isPresent())
            throw new UserAlreadyExistsException();

        User user = new User(firstName, lastName, email, password, confirmPassword,phoneNumber);
       return this.userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
    }

    @Override
    public User login(String email, String password) {
        if (email==null || email.isEmpty() || password==null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }
        return userRepository.findByEmailAndPassword(email,
                password).orElseThrow(InvalidUserCredentialsException::new);
    }

}
