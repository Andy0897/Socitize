package com.example.socitize.service;

import com.example.socitize.entity.Post;
import com.example.socitize.entity.User;
import com.example.socitize.repository.PostRepository;
import com.example.socitize.util.FileUploadUtil;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PostService {
    PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public String submitCreatePost(Post post, MultipartFile multipartFile) throws IOException {
        if (!multipartFile.isEmpty()) {
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            post.setImage(fileName);
            Post savedPost = postRepository.save(post);
            String upload = "/Posts/Images/" + post.getId();
            FileUploadUtil.saveFile(upload, fileName, multipartFile);
        } else {
            if(post.getImage().isEmpty()) {
                post.setImage(null);
                postRepository.save(post);
            }
        }
        postRepository.save(post);
        return "redirect:/profile";
    }
}

//https://www.youtube.com/watch?v=9uCAGlrpBbw