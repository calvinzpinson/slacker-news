package com.slackernews.service.Interface;

import com.slackernews.model.Comment;
import com.slackernews.model.Post;
import com.slackernews.model.User;

public interface ICommentService {
    Comment create(User user, Post post, String textContent);
}
