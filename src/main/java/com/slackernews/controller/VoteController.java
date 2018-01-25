package com.slackernews.controller;

import com.slackernews.model.Comment;
import com.slackernews.model.CommentCreationForm;
import com.slackernews.model.Post;
import com.slackernews.service.Interface.ICommentService;
import com.slackernews.service.Interface.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
public class VoteController {

    private IPostService postService;
    private ICommentService commentService;

    @Autowired
    public VoteController(IPostService postService, ICommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    //TODO: handle errors with a reasonable JSON reply, not an error
    @RequestMapping(value="/vote/post")
    public String upvotePost(@RequestParam("id") int id) {
        Post post = postService.getPostById(id).orElseThrow(() -> new NoSuchElementException("Post not found"));
        int currentPoints = post.getPoints();
        post.setPoints(currentPoints+1);
        postService.updatePostInfo(post);
        return "success";
    }

    //TODO: handle errors with a reasonable JSON reply, not an error
    @RequestMapping(value="/vote/comment")
    public String upvoteComment(@RequestParam("id") int id) {
        Comment comment = commentService.getCommentById(id).orElseThrow(() -> new NoSuchElementException("Comment not found"));
        int currentPoints = comment.getPoints();
        comment.setPoints(currentPoints+1);
        commentService.updateCommentInfo(comment);
        return "success";
    }
}
