package com.example.socitize.controller;

import com.example.socitize.entity.Post;
import com.example.socitize.repository.PostRepository;
import com.example.socitize.service.PostService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/post")
public class PostController {

    PostRepository postRepository;
    PostService postService;

    public PostController(PostRepository postRepository, PostService postService) {
        this.postRepository = postRepository;
        this.postService = postService;
    }

    @GetMapping("/create")
    public String getCreatePost(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return "post/create";
    }

    @PostMapping("/submit")
    public String submitCreatePost(@ModelAttribute @Valid Post post, BindingResult bindingResult, Model model) {

    }

    @GetMapping("/home")
    public String index(Model model){
        model.addAttribute("clothingItems", postRepository.findAll());
        return "index";
    }

    @GetMapping("/display/image/{id}")
    public void displayItemImage(@PathVariable long id, HttpServletResponse response) throws IOException {

        response.setContentType("image/*");

        Post post = postRepository.getPostById(id);

        InputStream is = new ByteArrayInputStream(post.getImage());
        IOUtils.copy(is, response.getOutputStream());
    }
}