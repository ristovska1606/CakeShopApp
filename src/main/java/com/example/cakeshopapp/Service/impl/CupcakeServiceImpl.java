package com.example.cakeshopapp.Service.impl;
import com.example.cakeshopapp.Models.Cupcake;
import com.example.cakeshopapp.Models.Flavor;
import com.example.cakeshopapp.Models.exceptions.ProductDoesntExist;
//import com.example.cakeshopapp.Repository.CakeRepository;
import com.example.cakeshopapp.Repository.CupcakeRepository;
import com.example.cakeshopapp.Repository.FlavorsRepository;
import com.example.cakeshopapp.Service.CupcakeService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CupcakeServiceImpl implements CupcakeService {
    private final CupcakeRepository cupcakeRepository;
    private final FlavorsRepository flavorsRepository;
    public CupcakeServiceImpl(CupcakeRepository cupcakeRepository, FlavorsRepository flavorsRepository) {
        this.cupcakeRepository = cupcakeRepository;
        this.flavorsRepository = flavorsRepository;
    }




    @Override
    public Cupcake create(String name, String description, String flavors, int price) {
        if(cupcakeRepository.findByName(name) != null)
        {
            this.flavorsRepository.deleteAll(this.cupcakeRepository.findByName(name).getFlavors());
            this.cupcakeRepository.delete(cupcakeRepository.findByName(name));
        }
        List<String> flavorsList = Arrays.stream(flavors.split(",")).toList();
        List<Flavor> flavors1 = flavorsList.stream().map(f -> new Flavor(f.trim())).toList();
        this.flavorsRepository.saveAll(flavors1);
        Cupcake cupcake = new Cupcake(name, description, flavors1, price);
        return this.cupcakeRepository.save(cupcake);
    }

    @Override
    public Cupcake edit(Long id, String name, String description, String flavors, int price) {
        List<String> flavorsList = Arrays.stream(flavors.split(",")).toList();
        List<Flavor> flavors1 = flavorsList.stream().map(f -> new Flavor(f)).toList();
        this.flavorsRepository.saveAll(flavors1);
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
