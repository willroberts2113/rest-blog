package com.example.restblog.data;

import org.springframework.data.jpa.repository.JpaRepository;


// TODO: need PostsRepository
public interface PostsRepository extends JpaRepository<Post, Long> {
}
