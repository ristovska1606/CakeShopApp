package com.example.cakeshopapp.Service;

import com.example.cakeshopapp.Models.Cake;

import java.io.File;
import java.util.List;
import java.util.Optional;

public interface CakeService {
    Cake create(String name, String description,String flavors, int price,  String image);
    Cake edit(Long id, String name, String description, String flavors, int price,  String image);
    void delete(Long id);
    List<Cake> listAll();
    Cake findById(Long id);
    Optional<Cake> findByIdOptional(Long id);
}
