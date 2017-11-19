package com.slackernews.service.Interface;

import com.slackernews.model.Comment;
import com.slackernews.model.CommentCreationForm;
import com.slackernews.model.Post;
import com.slackernews.model.User;

public interface ICommentService {
    public Comment create(CommentCreationForm form, User user);
}