package com.example.cakeshopapp.Service;

import com.example.cakeshopapp.Models.Product;

import java.util.List;

public interface ProductService {
    Product create(String productName, String flavor, int quantity, int price);
    Product edit(Long id, String productName, String flavor, int quantity, int price);
    void delete(Long id);
    List<Product> listAll();
    Product findById(Long id);
}
