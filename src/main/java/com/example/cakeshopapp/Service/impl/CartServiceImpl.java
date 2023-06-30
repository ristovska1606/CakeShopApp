package com.example.cakeshopapp.Service.impl;

import com.example.cakeshopapp.Models.Cart;
import com.example.cakeshopapp.Models.Product;
import com.example.cakeshopapp.Models.User;
import com.example.cakeshopapp.Models.enums.CartStatus;
import com.example.cakeshopapp.Models.exceptions.CartNotFoundException;
import com.example.cakeshopapp.Models.exceptions.ProductAlreadyInShoppingCartException;
import com.example.cakeshopapp.Models.exceptions.UserNotFoundException;
import com.example.cakeshopapp.Repository.CartRepository;
import com.example.cakeshopapp.Repository.ProductRepository;
import com.example.cakeshopapp.Service.CartService;
import com.example.cakeshopapp.Service.ProductService;
import com.example.cakeshopapp.Service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserService userService;
    private final ProductService productService;
    private final ProductRepository productRepository;

    public CartServiceImpl(CartRepository cartRepository, UserService userService, ProductService productService,
                           ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.productService = productService;
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> listAllProductsInShoppingCart(Long cartId) {
        if(!this.cartRepository.findById(cartId).isPresent())
            throw new CartNotFoundException();
        return this.cartRepository.findById(cartId).get().getProducts();
    }

    @Override
    public Cart getActiveShoppingCart(Long id) {
        User user = this.userService.findById(id);

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
        shoppingCart.setTotal(product.getPrice());
        return this.cartRepository.save(shoppingCart);
    }

    @Override
    public boolean deleteAllProductFromCart(Long cartId) {
        Cart cart = this.cartRepository.findById(cartId).orElseThrow();
        cart.setProducts(null);
        productRepository.deleteAll(cart.getProducts());
        return true;
    }

}

