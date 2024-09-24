package com.example.socitize.service;

import com.example.socitize.repository.AvatarRepository;
import com.example.socitize.repository.UserRepository;
import com.example.socitize.entity.User;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Service
public class UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private AvatarRepository avatarRepository;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, AvatarRepository avatarRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.avatarRepository = avatarRepository;
    }

    public String submitUser(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors() || checkIfExistsUserByUsername(user.getUsername()) || checkIfExistsUserByEmail(user.getEmail())) {
            model.addAttribute("existsUserByUsername", checkIfExistsUserByUsername(user.getUsername()));
            model.addAttribute("existsUserByEmail", checkIfExistsUserByEmail(user.getEmail()));
            model.addAttribute("user", user);
            model.addAttribute("avatars", avatarRepository.findAll());
           return "sign-up";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
        return "redirect:/sign-in";
    }

    public String submitEditUser(User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("avatars", avatarRepository.findAll());
        userRepository.save(user);
        return "redirect:/profile";
    }

    public boolean checkIfExistsUserByUsername(String username) {
        User user = userRepository.getUserByUsername(username);
        if (user == null) {
            return false;
        }
        return true;
    }

    public boolean checkIfExistsUserByEmail(String email) {
        User user = userRepository.getUserByEmail(email);
        if (user == null) {
            return false;
        }
        return true;
    }

    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }


    //https://cdn-icons-png.flaticon.com/128/2202/2202112.png
    //https://cdn-icons-png.flaticon.com/512/3135/3135715.png
}