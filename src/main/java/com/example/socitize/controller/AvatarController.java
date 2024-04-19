package com.example.socitize.controller;

import com.example.socitize.entity.Avatar;
import com.example.socitize.repository.AvatarRepository;
import com.example.socitize.service.AvatarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
