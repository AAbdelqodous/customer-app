package com.elite.customer_app.controller;

import com.elite.customer_app.dto.UserDto;
import com.elite.customer_app.model.UserEntity;
import com.elite.customer_app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") UserDto user,
                           BindingResult bindingResult,
                           Model model){
        System.out.println("---------------------- in register() -------------------");
        UserEntity userEntityByEmail = userService.findByEmail(user.getEmail());
        if (userEntityByEmail != null && userEntityByEmail.getEmail() != null && !userEntityByEmail.getEmail().isEmpty()){
            return "redirect:/register?fail";
        }

        UserEntity userEntityByUsername = userService.findByUsername(user.getUsername());
        if (userEntityByUsername != null && userEntityByUsername.getUsername() != null && !userEntityByUsername.getUsername().isEmpty()){
            return "redirect:/register?fail";
        }

        if (bindingResult.hasErrors()){
            model.addAttribute("user", user);
            return "register";
        }

        userService.save(user);
        return "redirect:/customers?success";
    }
}
