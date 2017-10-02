package com.slackernews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.slackernews.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Post findById(Integer id);
}
