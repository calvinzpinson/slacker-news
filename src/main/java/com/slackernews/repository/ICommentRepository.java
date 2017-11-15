package com.slackernews.repository;

import com.slackernews.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import com.slackernews.model.Comment;

import java.util.List;

public interface ICommentRepository extends JpaRepository<Comment, Integer> {
    Comment findById(Integer id);
    List<Comment> findAllByPost(Post post);
}