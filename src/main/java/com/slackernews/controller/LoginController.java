package com.slackernews.controller;

import com.slackernews.model.Validator.UserCreationFormValidator;
import com.slackernews.service.Implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class LoginController {

    private final UserService userService;
    private final UserCreationFormValidator userCreateFormValidator;

    @Autowired
    public LoginController(UserService userService, UserCreationFormValidator userCreationFormValidator) {
        this.userService = userService;
        this.userCreateFormValidator = userCreationFormValidator;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginPage(@RequestParam Optional<String> error) {
        return new ModelAndView("login", "error", error);
    }

}
