package com.example.cakeshopapp.Web;

import com.example.cakeshopapp.Models.Cupcake;
import com.example.cakeshopapp.Models.exceptions.PasswordDoNotMatchException;
import com.example.cakeshopapp.Models.exceptions.UserAlreadyExistsException;
import com.example.cakeshopapp.Service.CakeService;
import com.example.cakeshopapp.Service.CupcakeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final CakeService cakeService;
    private final CupcakeService cupcakeService;
    public HomeController(CakeService cakeService, CupcakeService cupcakeService) {
        this.cakeService = cakeService;
        this.cupcakeService = cupcakeService;
    }


    @GetMapping({"/", "/welcome"})
    public String welcome() {;
        return "welcomePage.html";
    }

    @GetMapping("/home")
    public String homePage() {;
        return "home.html";
    }

    @GetMapping("/addProduct")
    public String addProductPage() {;
        return "addProduct.html";
    }

    @PostMapping("/addProduct")
    public String register(@RequestParam String productName,
                           @RequestParam String productDescription,
                           @RequestParam String flavors,
                           @RequestParam String price,
                           @RequestParam String type) {

        if(type.equals("CAKE"))
            this.cakeService.create(productName, productDescription, flavors, Integer.parseInt(price));


        if(type.equals("CUPCAKE"))
            this.cupcakeService.create(productName, productDescription, flavors, Integer.parseInt(price));

        return "redirect:/home";

    }




}
