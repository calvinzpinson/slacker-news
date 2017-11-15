package com.slackernews.controller;

import com.slackernews.model.CurrentUser;
import com.slackernews.model.Post;
import com.slackernews.model.User;
import com.slackernews.service.Interface.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class CommentController {

    private ICommentService commentService;

    @Autowired
    public CommentController(ICommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String handleCommentCreationForm(String textContent, Post post, Principal principal) {
        User user = ((CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        commentService.create(user, post, textContent);

        return post.getURL().toString();
    }
}
