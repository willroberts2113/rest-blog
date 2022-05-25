package com.example.restblog.service;


import com.example.restblog.data.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// @Service allows us to register and inject UserService into any other class we want using Spring's IoC Container
@Service
public class UserService {

    // TODO: break out post related methods to post service

    // TODO: inject UsersRepository and PostsRepository into UserService class via constructor injection
    private final UsersRepository usersRepository;
    private final PostsRepository postsRepository;
    private final CategoriesRepository categoriesRepository;

    public UserService(UsersRepository usersRepository, PostsRepository postsRepository, CategoriesRepository categoriesRepository) {
        this.usersRepository = usersRepository;
        this.postsRepository = postsRepository;
        this.categoriesRepository = categoriesRepository;
    }

    public List<User> getUsersList() { // TODO: rename this 'getAllUsers'
        return usersRepository.findAll();
    }

    public List<Post> getPostList() { // TODO rename this to getAllPosts
        return postsRepository.findAll();
    }

    // We need to associate posts and users here
    public void addPost(Post newPost, String username) {

        // get the User object who made the post
        User user = getUserByUsername(username);

        // associate the post with the user object
        user.getPosts().add(newPost);
        // associate the *user* with the post object
        newPost.setUser(user);

        List<Category> categoriesToAdd = new ArrayList<>();

        for (Category category : newPost.getCategories()) {
            categoriesToAdd.add(categoriesRepository.findCategoryByName(category.getName()));
        }

        newPost.setCategories(categoriesToAdd);

        // TODO: call postsRepository.save(newPost)
        postsRepository.save(newPost);
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

    public void updatePost(long postId, Post post) {
        Post postToUpdate = postsRepository.findById(postId).orElseThrow();

        // TODO: Safety first!
        if (post.getContent() != null && !post.getContent().isEmpty()) {
            postToUpdate.setContent(post.getContent());
        }
        if (post.getTitle() != null && !post.getTitle().isEmpty()) {
            postToUpdate.setTitle(post.getTitle());
        }

        postsRepository.save(postToUpdate);
    }

    public void deletePostById(long id) {
        // TODO: change old code to postsRepository.deleteById(id)
        postsRepository.deleteById(id);
    }

    public List<Post> getPostsByTitleKeyword(String keyword) {
        return postsRepository.searchByTitleLike(keyword);
    }

}
