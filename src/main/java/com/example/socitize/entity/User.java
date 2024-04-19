package com.example.socitize.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
    @Column(name = "user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotEmpty(message = "This field can't be empty")
    @Size(min = 6, message = "Size must be greater than 6")
    @Size(max = 14, message = "Size must be smaller than 14")
    private String username;
    @NotEmpty(message = "This field can't be empty")
    @Size(min = 5, message = "Size must be greater than 5")
    private String email;
    @NotEmpty(message = "This field can't be empty")
    @Size(min = 8, message = "Size must be greater than 8")
    @Size(max = 1000, message = "Size must be smaller than 16")
    private String password;
    private String role;
    private boolean enable;
    @Size(min = 2, message = "Size must be greater than 2")
    @Size(max = 16, message = "Size must be smaller than 16")
    private String name;
    @Size(min = 2, message = "Size must be greater than 2")
    @Size(max = 100, message = "Size must be smaller than 16")
    private String bio;
    @NotNull(message = "Please choose avatar")
    @ManyToOne
    private Avatar avatar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}