package com.slackernews.controller;

import com.slackernews.model.CurrentUser;
import com.slackernews.model.Post;
import com.slackernews.model.PostCreationForm;
import com.slackernews.model.User;
import com.slackernews.service.Interface.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import javax.validation.Valid;
import java.net.MalformedURLException;
import java.security.Principal;
import java.util.NoSuchElementException;

@Controller
public class PostController {

    private IPostService postService;

    @Autowired
    public PostController(IPostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public ModelAndView getPostCreationPage() {
        return new ModelAndView("submit", "form", new PostCreationForm());
    }

    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    public String handlePostCreationForm(@Valid @ModelAttribute("form") PostCreationForm form, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "submit";
        }
        try {
            User user = ((CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
            postService.create(form, user);
        } catch (MalformedURLException e) { //TODO: Add logging for the exception
            bindingResult.reject("malformed.url", "The url is incorrectly formed");
            return "submit";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public ModelAndView getPostPage(@PathVariable int id) {
        return new ModelAndView("postView", "post", postService.getPostById(id)
                .orElseThrow(() -> new NoSuchElementException("Post not found")));
    }
}
