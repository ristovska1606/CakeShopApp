package com.example.cakeshopapp.Web;

import com.example.cakeshopapp.Service.CakeService;
import com.example.cakeshopapp.Service.CupcakeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class ProductsController {
    public static String UPLOAD_DIRECTORY = "src/main/resources/static/images/products/";
    private final CakeService cakeService;
    private final CupcakeService cupcakeService;

    public ProductsController(CakeService cakeService, CupcakeService cupcakeService) {
        this.cakeService = cakeService;
        this.cupcakeService = cupcakeService;
    }


    @GetMapping("/addProduct")
    public String addProductPage() {;
        return "addProduct.html";
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestParam String productName,
                             @RequestParam String productDescription,
                             @RequestParam("productImage") MultipartFile file,
                             @RequestParam String flavors,
                             @RequestParam String price,
                             @RequestParam String type) throws IOException {

        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, file.getOriginalFilename());
        Files.write(fileNameAndPath, file.getBytes());
        String productPath = "..\\..\\images\\products";
        Path imagePath = Paths.get(productPath, file.getOriginalFilename());

        if(type.equals("CAKE"))
            this.cakeService.create(productName, productDescription, flavors, Integer.parseInt(price), String.valueOf(imagePath));


        if(type.equals("CUPCAKE"))
            this.cupcakeService.create(productName, productDescription, flavors, Integer.parseInt(price), String.valueOf(imagePath));

        return "redirect:/home";

    }


}
