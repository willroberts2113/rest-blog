package com.example.restblog.service;

import com.example.restblog.data.*;
import org.springframework.stereotype.Service;

import java.util.List;

// @Service allows us to register and inject UserService into any other class we want using Spring's IoC Container
@Service
public class UserService {

    // TODO: break out post related methods to post service

    // TODO: inject UsersRepository and PostsRepository into UserService class via constructor injection
    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> getUsersList() { // TODO: rename this 'getAllUsers'
        return usersRepository.findAll();
    }

    // Taken from UsersController
    public User getUserById(Long id) {
        // TODO: use usersRepository.findById(id).orElseThrow()
        return usersRepository.findById(id).orElseThrow(); //throws an exception if the user cannot be found by id
    }

    // Taken from UsersController
    public User getUserByUsername(String username) {
        // TODO: don't forget to change this to usersRepository.findByUsername(username)
        return usersRepository.findByUsername(username);
    }

    public void updateEmail(Long userId, String newEmail){
        User user = getUserById(userId);
        user.setEmail(newEmail);
        usersRepository.save(user);
    }

}
