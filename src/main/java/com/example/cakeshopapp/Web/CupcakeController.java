package com.example.cakeshopapp.Web;

import com.example.cakeshopapp.Models.Cake;
import com.example.cakeshopapp.Models.Cupcake;
import com.example.cakeshopapp.Service.CupcakeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cupcakes")
public class CupcakeController {

    private final CupcakeService cupcakeService;

    public CupcakeController(CupcakeService cupcakeService) {
        this.cupcakeService = cupcakeService;
    }

    @GetMapping
    public String getCakesPage(Model model){
        List<Cupcake> cupcakes = this.cupcakeService.listAll();
        model.addAttribute("cupcakes", cupcakes);
        return "cupcakes.html";
    }

}
