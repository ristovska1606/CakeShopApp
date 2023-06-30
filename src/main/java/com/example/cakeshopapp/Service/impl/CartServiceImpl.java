package com.example.cakeshopapp.Service.impl;

import com.example.cakeshopapp.Models.Cart;
import com.example.cakeshopapp.Models.Product;
import com.example.cakeshopapp.Models.User;
import com.example.cakeshopapp.Models.enums.CartStatus;
import com.example.cakeshopapp.Models.exceptions.CartNotFoundException;
import com.example.cakeshopapp.Models.exceptions.ProductAlreadyInShoppingCartException;
import com.example.cakeshopapp.Models.exceptions.UserNotFoundException;
import com.example.cakeshopapp.Repository.CartRepository;
import com.example.cakeshopapp.Repository.UserRepository;
import com.example.cakeshopapp.Service.CartService;
import com.example.cakeshopapp.Service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productService = productService;
    }

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductService productService;



    @Override
    public List<Product> listAllProductsInShoppingCart(Long cartId) {
        if(!this.cartRepository.findById(cartId).isPresent())
            throw new CartNotFoundException();
        return this.cartRepository.findById(cartId).get().getProducts();
    }

    @Override
    public Cart getActiveShoppingCart(Long id) {
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());

        return this.cartRepository
                .findByUserAndStatus(user, CartStatus.CREATED)
                .orElseGet(() -> {
                    Cart cart = new Cart(user);
                    return this.cartRepository.save(cart);
                });
    }

    @Override
    public Cart addProductToShoppingCart(Long id, Long productId) {
        Cart shoppingCart = this.getActiveShoppingCart(id);
        Product product = this.productService.findById(productId);
        if(shoppingCart.getProducts()
                .stream().filter(i -> i.getProductId().equals(productId))
                .collect(Collectors.toList()).size() > 0)
            throw new ProductAlreadyInShoppingCartException();
        shoppingCart.getProducts().add(product);
        return this.cartRepository.save(shoppingCart);
    }

//    @Override
//    public boolean deleteProductFromCart(Long productId, Long cartId) {
//        Cart cart = this.cartRepository.findById(cartId).orElseThrow();
//        List<Product> products = listAllProductsInShoppingCart(cartId);
//        products = products.d
//
//
//    }
}

