package com.example.socitize.repository;

import com.example.socitize.entity.Post;
import com.example.socitize.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
    public Post getPostById(long id);
}
