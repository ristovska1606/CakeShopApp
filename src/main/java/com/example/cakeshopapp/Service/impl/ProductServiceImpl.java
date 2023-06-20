package com.example.cakeshopapp.Service.impl;

import com.example.cakeshopapp.Models.Product;
import com.example.cakeshopapp.Models.exceptions.OrderNotFoundException;
import com.example.cakeshopapp.Repository.ProductRepository;
import com.example.cakeshopapp.Service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Product create(String productName, String flavor, int quantity, int price) {
        Product product = new Product(productName, flavor, quantity, price);
        return this.productRepository.save(product);
    }

    @Override
    public Product edit(Long id, String productName, String flavor, int quantity, int price) {
        Product product = this.productRepository.findById(id).orElseThrow(() -> new OrderNotFoundException());
        product.setFlavor(flavor);
        product.setQuantity(quantity);
        product.setPrice(price);
        product.setProductName(productName);
        return this.productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        this.productRepository.deleteById(id);
    }

    @Override
    public List<Product> listAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return this.productRepository.findById(id).orElseThrow(() -> new OrderNotFoundException());
    }
}
