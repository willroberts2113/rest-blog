package com.example.restblog.web;

import com.example.restblog.data.Post;
import com.example.restblog.dto.CreatePostDto;
//import com.example.restblog.service.EmailService;
import com.example.restblog.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {

    private final PostService postService;
//    private final EmailService emailService;

    public PostsController(
            PostService postService
           /* EmailService emailService*/){
        this.postService = postService;
//        this.emailService = emailService;
    }

    @GetMapping
    public List<Post> getAll() {
        return postService.getPostList();
    }

    @GetMapping("{id}")
    public Post getById(@PathVariable Long id) {

        // TODO: refactor this all out of here
        for (Post post : postService.getPostList()) {
            if (Objects.equals(post.getId(), id)) {
                return post;
            }
        }
        return null;
    }

    @PostMapping
    public void createPost(@RequestBody Post postToAdd) {
        // TODO: If you want to associate the user to the post here, be sure to include a user object property on the post from the client side
        System.out.println(postToAdd);
    }

    @PostMapping("{username}")
    public void createByUsername(@PathVariable String username, @RequestBody CreatePostDto dto){
        // Nice and clean, huh?
        Post newPost = new Post();
        postService.addPost(dto, newPost,username);
//        emailService.prepareAndSend(newPost, "New Post Created", "You've created a new post.");
    }

    @PutMapping("{id}")
    public void updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
        // TODO: refactor this ALL out of here to a public method in UserService
        postService.updatePost(id, updatedPost);
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable Long id) {
        // TODO: add a public method in UserService to actually delete a Post by ID. Invoke that method here
        postService.deletePostById(id);
    }

    @GetMapping("search")
    public List<Post> searchPosts(@RequestParam String keyword) {
       return postService.getPostsByTitleKeyword(keyword);
    }

}
