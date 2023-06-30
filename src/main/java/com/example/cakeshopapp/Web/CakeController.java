package com.example.cakeshopapp.Web;

import com.example.cakeshopapp.Models.*;
import com.example.cakeshopapp.Service.CakeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cakes")
public class CakeController {
    private final CakeService cakeService;

    public CakeController(CakeService cakeService) {
        this.cakeService = cakeService;
    }


    @GetMapping
    public String getCakesPage(Model model){
        List<Cake> cakes = this.cakeService.listAll();
        model.addAttribute("cakes", cakes);
        Cake cake = cakes.get(0);
        return "cakes.html";
    }



}
