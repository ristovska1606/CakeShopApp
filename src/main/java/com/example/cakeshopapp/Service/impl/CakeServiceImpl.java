package com.example.cakeshopapp.Service.impl;

import com.example.cakeshopapp.Models.Cake;
import com.example.cakeshopapp.Models.Flavor;
import com.example.cakeshopapp.Models.exceptions.ProductDoesntExist;
import com.example.cakeshopapp.Repository.CakeRepository;
import com.example.cakeshopapp.Service.CakeService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CakeServiceImpl implements CakeService {

    private final CakeRepository cakeRepository;

    public CakeServiceImpl(CakeRepository cakeRepository) {
        this.cakeRepository = cakeRepository;
    }

    @Override
    public Cake create(String name, String description, String flavors, int price) {
        List<String> flavorsList = Arrays.stream(flavors.split(",")).toList();
        List<Flavor> flavors1 = flavorsList.stream().map(f -> new Flavor(f)).toList();
        Cake cake = new Cake(name, description, flavors1, price);
        return this.cakeRepository.save(cake);
    }

    @Override
    public Cake edit(Long id, String name, String description, String flavors, int price) {
        List<String> flavorsList = Arrays.stream(flavors.split(",")).toList();
        List<Flavor> flavors1 = flavorsList.stream().map(f -> new Flavor(f)).toList();
        Cake cake = cakeRepository.findById(id).orElseThrow(() -> new ProductDoesntExist());
        cake.setName(name);
        cake.setDescription(description);
        cake.setFlavors(flavors1);
        cake.setPriceFor10Servings(price);
        return this.cakeRepository.save(cake);
    }

    @Override
    public void delete(Long id) {
        this.cakeRepository.deleteById(id);
    }

    @Override
    public List<Cake> listAll() {
        return this.cakeRepository.findAll();
    }

    @Override
    public Cake findById(Long id) {
        return this.cakeRepository.findById(id).orElseThrow(() -> new ProductDoesntExist());
    }
}
