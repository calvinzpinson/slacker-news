package com.slackernews.controller;

import com.slackernews.model.*;
import com.slackernews.service.Interface.ICommentService;
import com.slackernews.service.Interface.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.net.MalformedURLException;
import java.security.Principal;
import java.util.NoSuchElementException;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
public class CommentController {

    private ICommentService commentService;
    private IPostService postService;

    @Autowired
    public CommentController(ICommentService commentService, IPostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    public RedirectView handleCommentCreationForm(@Valid @ModelAttribute("form") CommentCreationForm form, BindingResult bindingResult, Principal principal) {
        String postUrl = "/post/" + form.getId();
        if (bindingResult.hasErrors()) {
            return new RedirectView(postUrl);
        }
        try {
            User user = ((CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
            commentService.create(form, user);
        } catch (Exception e) {
            bindingResult.reject("invalid.format", "The comment format is incorrect");
            return new RedirectView("/login");
        }
        return new RedirectView(postUrl);
    }
}