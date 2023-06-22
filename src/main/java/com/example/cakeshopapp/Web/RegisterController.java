package com.example.cakeshopapp.Web;

import com.example.cakeshopapp.Models.exceptions.PasswordDoNotMatchException;
import com.example.cakeshopapp.Models.exceptions.UserAlreadyExistsException;
import com.example.cakeshopapp.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    public RegisterController( UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage() {
        return "register.html";
    }

    @PostMapping
    public String register(@RequestParam String firstName,
                           @RequestParam String lastName,
                           @RequestParam String email,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String phoneNumber,
                           Model model) {

        try{
            this.userService.create(firstName, lastName, email, password, repeatedPassword, phoneNumber);
            return "redirect:/login";
        }catch (PasswordDoNotMatchException | UserAlreadyExistsException  exceptions){
            model.addAttribute("hasErrors", true);
            model.addAttribute("errors", exceptions.getMessage());
            return  "register";
        }


    }
}
