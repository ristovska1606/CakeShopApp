package com.example.cakeshopapp.Service;

import com.example.cakeshopapp.Models.Cart;
import com.example.cakeshopapp.Models.Product;

import java.util.List;

public interface CartService {
    List<Product> listAllProductsInShoppingCart(Long cartId);

    Cart getActiveShoppingCart(Long id);
    Cart addProductToShoppingCart(Long id, Long productId);

    boolean deleteAllProductFromCart(Long cartId);

}
