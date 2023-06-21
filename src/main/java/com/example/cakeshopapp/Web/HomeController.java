package com.example.cakeshopapp.Web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @GetMapping({"/", "/welcome"})
    public String welcome() {;
        return "welcomePage.html";
    }

    @GetMapping("/home")
    public String homePage() {;
        return "home.html";
    }
}
