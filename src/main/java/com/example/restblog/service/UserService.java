package com.example.restblog.service;


import com.example.restblog.data.Post;
import com.example.restblog.data.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// @Service allows us to register and inject UserService into any other class we want using Spring's IoC Container
@Service
public class UserService {

    // TODO: we refactored UsersController and PostsController to remove all the sausage-making of posts and users
    //  -> userList and posts are our pretend database for now
    private List<User> userList = setUserList();
    private List<Post> posts = setPostList();

    public List<User> getUsersList(){
        return userList;
    }

    public List<Post> getPostList(){
        return posts;
    }

    // We need to associate posts and users here
    public void addPost(Post newPost, String username){

        // get the User object who made the post
        User user = getUserByUsername(username);

        // associate the post with the user object
        user.getPosts().add(newPost);
        // associate the *user* with the post object
        newPost.setUser(user);

        // add the post to the post list (our pretend database)
        posts.add(newPost);
    }

    // Taken from UsersController
    public User getUserById(Long id){
        for (User user : userList){
            if (user.getId().equals(id)){
                return user;
            }
        }
        return null;
    }

    // Taken from UsersController
    public User getUserByUsername(String username){
        for (User user : userList){
            if (user.getUsername().equals(username)){
                return user;
            }
        };
        return null;
    }

    // Taken from UsersController
    private List<User> setUserList(){
        List<User> userList = new ArrayList<>();
        userList.add(new User(1L, "billybobboy", "billy@bob.com", "12345"));
        userList.add(new User(2L, "annarafael", "anna@gmail.com", "54321"));
        return userList;
    }

    // Taken from PostsController
    private List<Post> setPostList(){
        List<Post> postList = new ArrayList<>();
        postList.add(new Post(1L, "Cool title", "Cool content", userList.get(0)));
        postList.add(new Post(2L, "Fake title", "Fake content", userList.get(1)));
        postList.add(new Post(3L, "Not from DB", "Fake data", userList.get(0)));
        return postList;
    }
}
