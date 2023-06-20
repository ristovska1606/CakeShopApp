package com.example.cakeshopapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ServletComponentScan
public class CakeShopAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CakeShopAppApplication.class, args);
    }

}
