package com.example.cakeshopapp.Service;

import com.example.cakeshopapp.Models.Cake;
import com.example.cakeshopapp.Models.Cupcake;

import java.util.List;

public interface CupcakeService {
    Cupcake create(String name, String description, String flavors, int price);
    Cupcake edit(Long id, String name, String description, String flavors, int price);
    void delete(Long id);
    List<Cupcake> listAll();
    Cupcake findById(Long id);
}
