package com.slackernews.service.Interface;

import com.slackernews.model.Comment;
import com.slackernews.model.CommentCreationForm;
import com.slackernews.model.User;

public interface ICommentService {
    Comment create(CommentCreationForm form, User user);
}