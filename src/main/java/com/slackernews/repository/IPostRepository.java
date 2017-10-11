package com.slackernews.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slackernews.model.Post;

import java.util.Optional;

public interface IPostRepository extends JpaRepository<Post, Integer> {
}
