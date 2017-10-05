package com.slackernews.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slackernews.model.Comment;

public interface ICommentRepository extends JpaRepository<Comment, Integer> {
    Comment findById(Integer id);
}