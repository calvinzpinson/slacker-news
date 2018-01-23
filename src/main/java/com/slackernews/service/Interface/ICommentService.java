package com.slackernews.service.Interface;

import com.slackernews.model.Comment;
import com.slackernews.model.CommentCreationForm;
import com.slackernews.model.User;

import java.util.Optional;

public interface ICommentService {
    Comment create(CommentCreationForm form, User user);
    Optional<Comment> getCommentById(int id);
    Comment updateCommentInfo(Comment post);
}