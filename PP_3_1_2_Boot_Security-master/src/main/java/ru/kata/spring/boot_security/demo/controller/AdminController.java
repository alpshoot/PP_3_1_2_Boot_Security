package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Collection;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    public AdminController() {
    }

    @GetMapping(value = "/")
    public String allUsers(Model model) {
        model.addAttribute("userList", userService.getListFromService());
        return "Users";
    }

//    public String testList(Model model, Authentication authentication){
//        User user = userService.findByUsername(authentication.getName());
//        model.addAttribute("user", user);
//        model.addAttribute("userList",userService.getListFromService());
//        Collection<Role> roles = roleService.getAllRoles();
//        model.addAttribute("roles", roles);
//        //новый юзер
//        User newuser = new User();
//        model.addAttribute("user", user);
//        return "Users";
//}
    @GetMapping("/new")
    public String newUser(Model model) {
        User user = new User();
        Collection<Role> roles = roleService.getAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("roles", roles);
        return "new";
    }
//@RequestMapping("/new")
//public String addNewUser(Model model) {
//    User user = new User();
//    model.addAttribute("user", user);
//    return "new";
//}

    @PostMapping("/")
    public String createUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin/";
    }

//    @RequestMapping("/")
//    public String saveUser(@ModelAttribute("newuser") User user,
//                           @RequestParam(required = false, name = "role_id") Long[] role_id) {
//        user.setRoles(roleService.getAllRoles());
//        userService.saveUser(user);
//        return "redirect:/admin/";
//    }

    @GetMapping("/{id}/edit")
    public String edit(Model model,@PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUser(user);
        return "redirect:/admin/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/admin/";
    }
}
