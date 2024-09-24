package com.example.socitize.service;

import com.example.socitize.entity.Post;
import com.example.socitize.repository.PostRepository;
import com.example.socitize.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class PostService {
    PostRepository postRepository;
    UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public String submitPost(Post post, Principal principal) {
        post.setUser(userRepository.getUserByUsername(principal.getName()));
        postRepository.save(post);
        return "redirect:/post/show";
    }
}
