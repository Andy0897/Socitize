package com.example.socitize.controller;

import com.example.socitize.entity.User;
import com.example.socitize.repository.AvatarRepository;
import com.example.socitize.repository.UserRepository;
import com.example.socitize.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {
    UserService userService;
    AvatarRepository avatarRepository;

    public UserController(UserService userService, AvatarRepository avatarRepository) {
        this.userService = userService;
        this.avatarRepository = avatarRepository;
    }

    @GetMapping("/sign-in")
    public String getSignIn() {
        return "sign-in";
    }

    @GetMapping("/sign-up")
    public String getSignUp(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("avatars", avatarRepository.findAll());
        return "sign-up";
    }
    @GetMapping("/edit-profile")
    public String getEditProfile(Principal principal, Model model) {
        User user = userService.getUserByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("avatars", avatarRepository.findAll());
        System.out.println(user.getUsername());
        return "edit-profile";
    }

    @PostMapping("/submit-user")
    public String submitUser(User user, BindingResult bindingResult, Model model) {
        return userService.submitUser(user, bindingResult, model);
    }

    @PostMapping("/submit-edit-user")
    public String submitUserEdit(User user, BindingResult bindingResult, Model model) {
        return userService.submitEditUser(user, bindingResult, model);
    }

    @GetMapping("/profile")
    public String getProfile(Model model, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/access-denied")
    @ResponseBody
    public String getAccessDenied() {
        return "Access Denied";
    }
}