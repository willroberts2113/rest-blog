package com.example.restblog.service;


import com.example.restblog.data.Post;
import com.example.restblog.data.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private List<User> userList = setUserList();
    private List<Post> posts = setPostList();

    public List<User> getUsersList(){
        return userList;
    }

    public List<Post> getPostList(){
        return posts;
    }

    public User getUserById(Long id){
        for (User user : userList){
            if (user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }

    public User getUserByUsername(String username){
        for (User user : userList){
            if (user.getUsername().equals(username)){
                return user;
            }
        };
        return null;
    }

    private List<User> setUserList(){
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "billybobboy", "billy@bob.com", "12345"));
        userList.add(new User(2L, "annarafael", "anna@gmail.com", "54321"));
        return userList;
    }

    private List<Post> setPostList(){
        List<Post> postList = new ArrayList<>();
        postList.add(new Post(1L, "Cool title", "Cool content"));
        postList.add(new Post(2L, "Fake title", "Fake content"));
        postList.add(new Post(3L, "Not from DB", "Fake data"));
        return postList;
    }
}
