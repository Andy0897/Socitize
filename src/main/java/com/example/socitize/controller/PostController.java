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
import org.springframework.web.multipart.MultipartFile;

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
    public String submitCreatePost(@ModelAttribute @Valid Post post, @RequestParam("Image")MultipartFile multipartFile) throws IOException {
        return postService.submitCreatePost(post, multipartFile);
    }

    @GetMapping("/home") 
    public String getHome(Model model){
        model.addAttribute("posts", postRepository.findAll());
        return "home";
    }

    @GetMapping("/display/image/{id}")
    public void displayItemImage(@PathVariable long id, HttpServletResponse response) throws IOException {

        response.setContentType("image/*");

        Post post = postRepository.getPostById(id);


    }
}