package com.example.cakeshopapp.Web;

import com.example.cakeshopapp.Models.*;
import com.example.cakeshopapp.Models.enums.CartStatus;
import com.example.cakeshopapp.Models.exceptions.ProductDoesntExist;
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
    private final OrderService orderService;

    public CartController(CakeService cakeService, ProductService productService, FlavorsRepository flavorsRepository, CartService cartService, UserService userService, CupcakeService cupcakeService, OrderService orderService) {
        this.cakeService = cakeService;
        this.productService = productService;
        this.flavorsRepository = flavorsRepository;
        this.cartService = cartService;
        this.userService = userService;
        this.cupcakeService = cupcakeService;
        this.orderService = orderService;
    }

    @PostMapping("/addProductToCart")
    public String addProductToCart(@RequestParam Long productId,
                                   @RequestParam Long flavor,
                                   @RequestParam String serves,
                                   HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("user");
        Flavor cakeFlavor = this.flavorsRepository.findById(flavor).orElseThrow();
        Product product = null;

        try{
            Cupcake productForCart = cupcakeService.findById(productId);
            product = this.productService.create(productForCart.getName(),
                    cakeFlavor.getName(),
                    Integer.parseInt(serves),
                    Integer.parseInt(serves)*productForCart.getPriceFor10Servings()

            );
        }catch (ProductDoesntExist e){
            Cake productForCart = cakeService.findById(productId);
            product = this.productService.create(productForCart.getName(),
                    cakeFlavor.getName(),
                    Integer.parseInt(serves),
                    Integer.parseInt(serves)*productForCart.getPriceFor10Servings()
            );
        }

        cartService.addProductToShoppingCart(user.getUser_id(), product.getProductId());

        if(cakeService.findByIdOptional(productId).isEmpty())
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
        return "cart.html";
    }

    @PostMapping("/checkout")
    public String checkOut(@RequestParam String cartNumber,
                           @RequestParam String dateAndTime,
                           @RequestParam String address,
                           @RequestParam String notes,
                           HttpServletRequest request,
                           Model model){

        User user = (User) request.getSession().getAttribute("user");
        Cart cart = this.cartService.getActiveShoppingCart(user.getUser_id());
        this.cartService.markAsFinished(cart.getId());
        this.orderService.create(cart, cartNumber, dateAndTime,address, notes);
        return "redirect:/successfulOrder";
    }


    @GetMapping("/successfulOrder")
    public String successfulOrderPage(){
        return "successfulOrder.html";
    }
}
