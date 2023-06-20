package com.example.cakeshopapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ServletComponentScan
@EntityScan("com.example.cakeshopapp")
@EnableJpaRepositories("com.example.cakeshopapp")
@Configuration
public class CakeShopAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(CakeShopAppApplication.class, args);
    }

}
