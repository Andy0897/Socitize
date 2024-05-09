package com.example.socitize.service;

import com.example.socitize.entity.Avatar;
import com.example.socitize.repository.AvatarRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Service
public class AvatarService {
    AvatarRepository avatarRepository;

    public AvatarService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }

    public String submitAvatar(Avatar avatar, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("avatar", avatar);
            return "avatar/add";
        }
        avatarRepository.save(avatar);
        return "redirect:/avatar/show";
    }
}