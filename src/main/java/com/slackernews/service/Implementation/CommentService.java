package com.slackernews.service.Implementation;

import com.slackernews.model.*;
import com.slackernews.repository.ICommentRepository;
import com.slackernews.repository.IPostRepository;
import com.slackernews.service.Interface.ICommentService;
import com.slackernews.service.Interface.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService implements ICommentService {

    private ICommentRepository commentRepository;

    @Autowired
    public CommentService(ICommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getCommentsByPost() {
        return commentRepository.findAll();
    }

    public Comment create(String textContent, User user, Post post) {
        Comment comment = new Comment(user, post, textContent);

        return commentRepository.save(comment);
    }
}
