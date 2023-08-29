package com.example.cakeshopapp.Service.impl;

import com.example.cakeshopapp.Models.Cake;
import com.example.cakeshopapp.Models.Flavor;
import com.example.cakeshopapp.Models.exceptions.ProductDoesntExist;
import com.example.cakeshopapp.Repository.CakeRepository;
import com.example.cakeshopapp.Repository.FlavorsRepository;
import com.example.cakeshopapp.Service.CakeService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CakeServiceImpl implements CakeService {

    private final CakeRepository cakeRepository;
    private final FlavorsRepository flavorsRepository;

    public CakeServiceImpl(CakeRepository cakeRepository, FlavorsRepository flavorsRepository) {
        this.cakeRepository = cakeRepository;
        this.flavorsRepository = flavorsRepository;
    }


    @Override
    public Cake create(String name, String description, String flavors, int price,  String image) {
        if(cakeRepository.findByName(name) != null)
            {
                this.flavorsRepository.deleteAll(this.cakeRepository.findByName(name).getFlavors());
                this.cakeRepository.delete(cakeRepository.findByName(name));
            }
        List<String> flavorsList = Arrays.stream(flavors.split(",")).toList();
        List<Flavor> flavors1 = flavorsList.stream().map(f -> new Flavor(f.trim())).toList();
        this.flavorsRepository.saveAll(flavors1);
        Cake cake = new Cake(name, description, flavors1, price, image);
        return this.cakeRepository.save(cake);
    }

    @Override
    public Cake edit(Long id, String name, String description, String flavors, int price,  String image) {
        List<String> flavorsList = Arrays.stream(flavors.split(",")).toList();
        List<Flavor> flavors1 = flavorsList.stream().map(f -> new Flavor(f)).toList();
        this.flavorsRepository.saveAll(flavors1);
        Cake cake = cakeRepository.findById(id).orElseThrow(() -> new ProductDoesntExist());
        cake.setName(name);
        cake.setDescription(description);
        cake.setFlavors(flavors1);
        cake.setPriceFor10Servings(price);
        cake.setProductImage(image);
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

    @Override
    public Optional<Cake> findByIdOptional(Long id) {
        return this.cakeRepository.findById(id);
    }

    @Override
    public Optional<Cake> findByName(String name) {
        return Optional.ofNullable(this.cakeRepository.findByName(name));
    }
}
