package com.slackernews.service.Interface;

import com.slackernews.model.Post;

import java.util.Optional;

public interface IPostService {
    Optional<Post> getPostById(int id);
}
