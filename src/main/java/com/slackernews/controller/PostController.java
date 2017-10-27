package com.slackernews.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PostController {

    @RequestMapping(value = "/submit", method = RequestMethod.GET)
    public ModelAndView getHomePage() {
        return new ModelAndView("submit", "form", "form");
    }
}
