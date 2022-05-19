package com.example.restblog.web;

import com.example.restblog.data.Post;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/posts", headers = "Accept=application/json")
public class PostsController {

    List<Post> posts = setPostList();

    @GetMapping
    public List<Post> getAll() {
        return posts;
    }

    @GetMapping("{id}")
    public Post getById(@PathVariable Long id) {
        for (Post post : getAll()) {
            if (Objects.equals(post.getId(), id)) {
                return post;
            }
        }
        return new Post();
    }

    @PostMapping
    public void createPost(@RequestBody Post postToAdd) {
        System.out.println(postToAdd);
    }

    @PutMapping("{id}")
    public void updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
        for (Post post : posts){
            if (post.getId().equals(id)){
                post.setContent(updatedPost.getContent());
                post.setTitle(updatedPost.getTitle());
            }
        }
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable Long id) {
        System.out.println("Deleting post with id: " + id);
    }


    private List<Post> setPostList(){
        List<Post> postList = new ArrayList<>();
        postList.add(new Post(1L, "Cool title", "Cool content"));
        postList.add(new Post(2L, "Fake title", "Fake content"));
        postList.add(new Post(3L, "Not from DB", "Fake data"));
        return postList;
    }
}
