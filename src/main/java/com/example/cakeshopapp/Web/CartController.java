package com.example.cakeshopapp.Web;

import com.example.cakeshopapp.Models.Cake;
import com.example.cakeshopapp.Models.Flavor;
import com.example.cakeshopapp.Models.Product;
import com.example.cakeshopapp.Models.User;
import com.example.cakeshopapp.Repository.FlavorsRepository;
import com.example.cakeshopapp.Service.CakeService;
import com.example.cakeshopapp.Service.CartService;
import com.example.cakeshopapp.Service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {

    private final CakeService cakeService;
    private final ProductService productService;
    private final FlavorsRepository flavorsRepository;
    private final CartService cartService;

    public CartController(CakeService cakeService, ProductService productService, FlavorsRepository flavorsRepository, CartService cartService) {
        this.cakeService = cakeService;
        this.productService = productService;
        this.flavorsRepository = flavorsRepository;
        this.cartService = cartService;
    }

    @PostMapping("/addProductToCart")
    public String addProductToCart(@RequestParam Long cakeId,
                                   @RequestParam Long flavor,
                                   @RequestParam String serves,
                                   HttpServletRequest request){
        Cake cake = this.cakeService.findById(cakeId);
        Flavor cakeFlavor = this.flavorsRepository.findById(flavor).orElseThrow();
        Product product = this.productService.create(cake.getName(), cakeFlavor.getName(), Integer.parseInt(serves), Integer.parseInt(serves)*cake.getPriceFor10Servings());
        User user = (User) request.getSession().getAttribute("user");
        cartService.addProductToShoppingCart(user.getUser_id(), product.getProductId());

        if(cakeService.findById(cakeId) == null)
            return "redirect:/cupcakes";
        return "redirect:/cakes";

    }

}
