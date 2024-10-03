package com.example.socitize.controller;

import com.example.socitize.entity.Avatar;
import com.example.socitize.entity.User;
import com.example.socitize.repository.AvatarRepository;
import com.example.socitize.repository.PostRepository;
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
    UserRepository userRepository;
    PostRepository postRepository;

    public UserController(UserService userService, AvatarRepository avatarRepository, UserRepository userRepository, PostRepository postRepository) {
        this.userService = userService;
        this.avatarRepository = avatarRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
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

    @GetMapping("/home")
    public String getHome(Model model) {
        model.addAttribute("posts", postRepository.findAll());
        return "home";
    }

    @PostMapping("/submit-user")
    public String submitUser(User user, BindingResult bindingResult, Model model) {
        return userService.submitUser(user, bindingResult, model);
    }

    @GetMapping("/edit-avatar")
    public String getEditAvatar(Principal principal, Model model) {
        User user = userRepository.getUserByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("avatars", avatarRepository.findAll());
        return "edit-avatar";
    }

    @PostMapping("/submit-edit-avatar")
    public String submitEditAvatar(User user, Model model, Principal principal) {
        return userService.submitEditAvatar(user, model, principal);
    }

    @GetMapping("/profile")
    public String getProfile(Model model, Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("posts", postRepository.findByUserId(user.getId()));
        return "profile";
    }

    @GetMapping("/access-denied")
    @ResponseBody
    public String getAccessDenied() {
        return "Access Denied";
    }
}