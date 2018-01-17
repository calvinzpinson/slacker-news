package com.slackernews.controller;

import com.slackernews.model.*;
import com.slackernews.service.Interface.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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
    public String getPostPage(@PathVariable int id, Model model) {
        model.addAttribute("form", new CommentCreationForm());
        model.addAttribute("post", postService.getPostById(id).orElseThrow(() -> new NoSuchElementException("Post not found")));
        return "postView";
    }

    @RequestMapping(value="/vote", method = RequestMethod.GET)
    public String vote(@RequestParam("id") int id, Model model) {
        Post post = postService.getPostById(id).orElseThrow(() -> new NoSuchElementException("Post not found"));
        int currentPoints = post.getPoints();
        post.setPoints(currentPoints+1);
        postService.updatePostInfo(post);
        model.addAttribute("form", new CommentCreationForm());
        model.addAttribute("post", post);
        return "postView";
    }
}
