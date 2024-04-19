package com.example.socitize.repository;

import com.example.socitize.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    public User getUserByEmail(String email);
    public User getUserByUsername(String username);
}
