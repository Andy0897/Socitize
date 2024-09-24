package com.example.socitize.controller;

import com.example.socitize.entity.Post;
import com.example.socitize.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller("/post")
public class PostController {
    PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/create")
    public String createPost(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return "post/create";
    }

    @PostMapping("/submit")
    public String submitPost(Post post, Principal principal) {
        return postService.submitPost(post, principal);
    }
}