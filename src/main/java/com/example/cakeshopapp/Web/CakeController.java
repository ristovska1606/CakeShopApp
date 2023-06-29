package com.example.cakeshopapp.Web;

import com.example.cakeshopapp.Models.*;
import com.example.cakeshopapp.Repository.FlavorsRepository;
import com.example.cakeshopapp.Service.CakeService;
import com.example.cakeshopapp.Service.CartService;
import com.example.cakeshopapp.Service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cakes")
public class CakeController {
    private final CakeService cakeService;
    private final ProductService productService;
    private final FlavorsRepository flavorsRepository;
    private final CartService cartService;

    public CakeController(CakeService cakeService, ProductService productService, FlavorsRepository flavorsRepository, CartService cartService) {
        this.cakeService = cakeService;
        this.productService = productService;
        this.flavorsRepository = flavorsRepository;
        this.cartService = cartService;
    }

    @GetMapping
    public String getCakesPage(Model model){
        List<Cake> cakes = this.cakeService.listAll();
        model.addAttribute("cakes", cakes);
        Cake cake = cakes.get(0);
        return "cakes.html";
    }



}
