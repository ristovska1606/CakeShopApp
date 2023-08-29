package com.example.cakeshopapp.Web;

import com.example.cakeshopapp.Models.User;
import com.example.cakeshopapp.Service.UserService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/", "/welcome"})
    public String welcome() {;
        return "welcomePage.html";
    }

    @GetMapping("/home")
    public String homePage( HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if(user == null)
            return "welcomePage.html";
        String role = user.getRole().name();
        model.addAttribute("role", role);
        return "home.html";
    }




}
