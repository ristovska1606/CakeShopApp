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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class HomeController {


    public HomeController() {
    }

    @GetMapping({"/", "/welcome"})
    public String welcome() {;
        return "welcomePage.html";
    }

    @GetMapping("/home")
    public String homePage() {
        return "home.html";
    }




}
