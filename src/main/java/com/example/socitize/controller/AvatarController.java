package com.example.socitize.controller;

import com.example.socitize.entity.Avatar;
import com.example.socitize.repository.AvatarRepository;
import com.example.socitize.service.AvatarService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/avatar")
public class AvatarController {
    AvatarRepository avatarRepository;
    AvatarService avatarService;

    public AvatarController(AvatarRepository avatarRepository, AvatarService avatarService) {
        this.avatarRepository = avatarRepository;
        this.avatarService = avatarService;
    }

    @GetMapping("/add")
    public String getAddAvatar(Model model) {
        Avatar avatar = new Avatar();
        model.addAttribute("avatar", avatar);
        return "avatar/add-avatar";
    }

    @PostMapping("/submit")
    public String submitAvatar(@Valid @ModelAttribute Avatar avatar, BindingResult bindingResult, Model model) {
        return avatarService.submitAvatar(avatar, bindingResult, model);
    }

    @GetMapping("/show")
    public String getShowAvatar(Model model) {
        model.addAttribute("avatars", avatarRepository.findAll());
        return "avatar/show";
    }
}