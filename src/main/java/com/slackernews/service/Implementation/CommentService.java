package com.slackernews.service.Implementation;

import com.slackernews.model.Comment;
import com.slackernews.model.CommentCreationForm;
import com.slackernews.model.Post;
import com.slackernews.model.User;
import com.slackernews.repository.ICommentRepository;
import com.slackernews.service.Interface.ICommentService;
import com.slackernews.service.Interface.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CommentService implements ICommentService {

    private ICommentRepository commentRepository;
    private IPostService postService;

    @Autowired
    public CommentService(ICommentRepository commentRepository, IPostService postService) {
        this.commentRepository = commentRepository;
        this.postService = postService;
    }

    public List<Comment> getCommentsByPost() {
        return commentRepository.findAll();
    }

    @Override
    public Comment create(CommentCreationForm form, User user) {
        Post post = postService.getPostById(form.getId()).orElseThrow(() -> new NoSuchElementException("Post not found"));
        Comment comment = new Comment(user, post, form.getText());

        return commentRepository.save(comment);
    }
}
