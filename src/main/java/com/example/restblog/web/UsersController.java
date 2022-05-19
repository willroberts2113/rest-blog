package com.example.restblog.web;


import com.example.restblog.data.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users", headers = "Accept=application/json")
public class UsersController {

    private List<User> userList = setUserList();

    @GetMapping
    public List<User> getAll(){
        return userList;
    }

    @GetMapping("{id}")
    public User getById(@PathVariable long id){
        for (User user : userList){
            if (user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }

    @PostMapping
    public void create(@RequestBody User newUser){
        userList.add(newUser);
    }

    private List<User> setUserList(){
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "billybobboy", "billy@bob.com", "12345"));
        userList.add(new User(2L, "annarafael", "anna@gmail.com", "54321"));
        return userList;
    }
}
