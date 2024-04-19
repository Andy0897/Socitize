package com.example.socitize.entity;

import jakarta.persistence.*;

@Table(name = "avatars")
@Entity
public class Avatar {
    @Column(name = "avatar_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String url;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
