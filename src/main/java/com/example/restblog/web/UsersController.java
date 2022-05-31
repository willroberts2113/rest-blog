package com.example.restblog.web;


import com.example.restblog.data.Post;
import com.example.restblog.data.User;
import com.example.restblog.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users", headers = "Accept=application/json")
public class UsersController {

    // Once the adding and getting of users is removed, we have to inject the UserService into the controller
    private final UserService userService;
    private PasswordEncoder passwordEncoder;

    public UsersController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService; // injection point of UserService
        this.passwordEncoder = passwordEncoder;
    }

    // TODO: once all the code for users is taken out, be sure to refactor the controller methods to call on UserService!
    @GetMapping
    public List<User> getAll(){
        return userService.getUsersList();
    }

    @GetMapping("{id}")
    public User getById(@PathVariable long id){
        return userService.getUserById(id);
    }


    // TODO: add "create" to the path
    @PostMapping("create")
    public void create(@RequestBody User newUser){
        // TODO: inject the PasswordEncoder and set the incoming password as below
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        userService.createUser(newUser);
    }

    @PostMapping("{username}")
    public void addUserPost(@PathVariable String username, @RequestBody Post newPost){
        User user = userService.getUserByUsername(username);
        user.getPosts().add(newPost);
    }

    @GetMapping("username")
    public User getByUsername(@RequestParam String username) {
        System.out.println("Getting user with username: " + username);
        return userService.getUserByUsername(username);
    }

    @GetMapping("email")
    public User getByEmail(@RequestParam String email) {
        System.out.println("Getting user with email: " + email);
        return null;
    }

    @PutMapping("{id}/updatePassword")
    public void updatePassword(@PathVariable Long id, @RequestParam(required = false) String oldPassword, @Valid @Size(min = 3) @RequestParam String newPassword) {
        // TODO: remove this code to the UserService in a public method which actually updates the password of a real user
        User userToUpdate = getById(id);
        userToUpdate.setPassword(newPassword);
        System.out.println(userToUpdate.getPassword());
    }

    @PatchMapping("{userId}")
    public void updateEmail(@PathVariable Long userId, @RequestParam String newEmail){
        userService.updateEmail(userId, newEmail);
    }



}
