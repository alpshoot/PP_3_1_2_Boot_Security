package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;

@Controller
public class UserController {
   private  UserServiceImpl userServiceimpl;

   @Autowired
    public UserController(UserServiceImpl userServiceimpl) {
        this.userServiceimpl = userServiceimpl;
    }

    @GetMapping("/user")
    public String pageForUser(Principal principal, Model model) {
        User user = userServiceimpl.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user";
    }
}
