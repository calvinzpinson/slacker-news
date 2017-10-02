package com.slackernews.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slackernews.model.Post;

public interface IPostRepository extends JpaRepository<Post, Integer> {
    Post findById(Integer id);
}
