package com.example.cakeshopapp.Web;

import com.example.cakeshopapp.Models.*;
import com.example.cakeshopapp.Models.enums.CartStatus;
import com.example.cakeshopapp.Repository.FlavorsRepository;
import com.example.cakeshopapp.Service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CartController {

    private final CakeService cakeService;
    private final ProductService productService;
    private final FlavorsRepository flavorsRepository;
    private final CartService cartService;
    private final UserService userService;
    private final CupcakeService cupcakeService;

    public CartController(CakeService cakeService, ProductService productService, FlavorsRepository flavorsRepository, CartService cartService, UserService userService, CupcakeService cupcakeService) {
        this.cakeService = cakeService;
        this.productService = productService;
        this.flavorsRepository = flavorsRepository;
        this.cartService = cartService;
        this.userService = userService;
        this.cupcakeService = cupcakeService;
    }

    @PostMapping("/addProductToCart")
    public String addProductToCart(@RequestParam Long productId,
                                   @RequestParam Long flavor,
                                   @RequestParam String serves,
                                   HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("user");
        Flavor cakeFlavor = this.flavorsRepository.findById(flavor).orElseThrow();
        Product product = null;

        if(cakeService.findById(productId) == null)
            {
                Cupcake productForCart = cupcakeService.findById(productId);
                product = this.productService.create(productForCart.getName(),
                        cakeFlavor.getName(),
                        Integer.parseInt(serves),
                        Integer.parseInt(serves)*productForCart.getPriceFor10Servings()

                );

            }
        else {
            Cake productForCart = cakeService.findById(productId);
            product = this.productService.create(productForCart.getName(),
                    cakeFlavor.getName(),
                    Integer.parseInt(serves),
                    Integer.parseInt(serves)*productForCart.getPriceFor10Servings()
            );
        }

        cartService.addProductToShoppingCart(user.getUser_id(), product.getProductId());

        if(cakeService.findById(productId) == null)
            return "redirect:/cupcakes";
        return "redirect:/cakes";

    }


    @GetMapping("/cart")
    public String cart(Model model, HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        Cart cart = this.cartService.getActiveShoppingCart(user.getUser_id());
        List<Product> products = this.cartService.listAllProductsInShoppingCart(cart.getId());
        model.addAttribute("products", products);
        model.addAttribute("total", cart.getTotal());
        model.addAttribute("checkout", true);

        return "cart.html";
    }

    @PostMapping("/checkout")
    public String checkOut(@RequestParam String cartNumber,
                           @RequestParam String dateAndTime,
                           @RequestParam String notes,
                           HttpServletRequest request,
                           Model model){

        User user = (User) request.getSession().getAttribute("user");
        Cart cart = this.cartService.getActiveShoppingCart(user.getUser_id());
        cart.setStatus(CartStatus.FINISHED);
        this.cartService.deleteAllProductFromCart(cart.getId());
        model.addAttribute("checkout", true);
        model.addAttribute("message", "Your order has been placed.");

        return "redirect:/home";
    }


}
