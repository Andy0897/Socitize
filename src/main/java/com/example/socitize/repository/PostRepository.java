package com.example.socitize.repository;

import com.example.socitize.entity.Post;
import com.example.socitize.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    public List<Post> getPostsByUser(User user);
}
