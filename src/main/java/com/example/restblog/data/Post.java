package com.example.restblog.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Post {

    private Long id;
    private String title;
    private String content;

    @JsonIgnoreProperties("posts") // ignore the posts field on the User object to prevent extra data from being returned
    private User user; // each post has only 1 user who authored it

    public Post() {
    }

    public Post(Long id, String title, String content, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user; // TODO: for faking purposes, inject a user with this constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // TODO: don't forget getters and setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
