package com.example.cakeshopapp.Service;

import com.example.cakeshopapp.Models.Cake;

import java.io.File;
import java.util.List;

public interface CakeService {
    Cake create(String name, String description,String flavors, int price);
    Cake edit(Long id, String name, String description, String flavors, int price);
    void delete(Long id);
    List<Cake> listAll();
    Cake findById(Long id);
}
