package com.example.trackingV2.controller;

import com.example.trackingV2.model.Role;
import com.example.trackingV2.model.User;
import com.example.trackingV2.repository.RoleRepository;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import com.example.trackingV2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;


@Controller
public class UserController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    public UserController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        List<Role> roles = roleRepository.findAll();
        roles.remove(0);
        model.addAttribute("user", new User());
        model.addAttribute("roles", roles);
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute @Valid User user, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("errorMessage", "Вы ввели некорректные данные");
            return "error-page";
        }
        userService.save(user);
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

}
