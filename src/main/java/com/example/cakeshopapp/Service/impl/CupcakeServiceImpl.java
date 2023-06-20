package com.example.cakeshopapp.Service.impl;
import com.example.cakeshopapp.Models.Cupcake;
import com.example.cakeshopapp.Models.Flavor;
import com.example.cakeshopapp.Models.exceptions.ProductDoesntExist;
//import com.example.cakeshopapp.Repository.CakeRepository;
import com.example.cakeshopapp.Repository.CupcakeRepository;
import com.example.cakeshopapp.Service.CakeService;
import com.example.cakeshopapp.Service.CupcakeService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CupcakeServiceImpl implements CupcakeService {
    private final CupcakeRepository cupcakeRepository;
    public CupcakeServiceImpl(CupcakeRepository cupcakeRepository) {
        this.cupcakeRepository = cupcakeRepository;
    }




    @Override
    public Cupcake create(String name, String description, String flavors, int price) {
        List<String> flavorsList = Arrays.stream(flavors.split(",")).toList();
        List<Flavor> flavors1 = flavorsList.stream().map(f -> new Flavor(f)).toList();
        Cupcake cupcake = new Cupcake(name, description, flavors1, price);
        return this.cupcakeRepository.save(cupcake);
    }

    @Override
    public Cupcake edit(Long id, String name, String description, String flavors, int price) {
        List<String> flavorsList = Arrays.stream(flavors.split(",")).toList();
        List<Flavor> flavors1 = flavorsList.stream().map(f -> new Flavor(f)).toList();
        Cupcake cupcake = cupcakeRepository.findById(id).orElseThrow(() -> new ProductDoesntExist());
        cupcake.setName(name);
        cupcake.setDescription(description);
        cupcake.setFlavors(flavors1);
        cupcake.setPriceFor10Servings(price);
        return this.cupcakeRepository.save(cupcake);
    }

    @Override
    public void delete(Long id) {
        this.cupcakeRepository.deleteById(id);
    }

    @Override
    public List<Cupcake> listAll() {
        return this.cupcakeRepository.findAll();
    }

    @Override
    public Cupcake findById(Long id) {
        return this.cupcakeRepository.findById(id).orElseThrow(() -> new ProductDoesntExist());
    }
}
