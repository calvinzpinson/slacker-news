package com.slackernews.controller;

import com.slackernews.model.UserCreationForm;
import com.slackernews.model.Validator.UserCreationFormValidator;
import com.slackernews.service.Implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.NoSuchElementException;

@Controller
public class UserController {

    private final UserService userService;
    private final UserCreationFormValidator userCreateFormValidator;

    @Autowired
    public UserController(UserService userService, UserCreationFormValidator userCreationFormValidator) {
        this.userService = userService;
        this.userCreateFormValidator = userCreationFormValidator;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }

    @RequestMapping("/user/{id}")
    public ModelAndView getUserPage(@PathVariable int id) {
        return new ModelAndView("user", "user", userService.getUserById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("User=%s not found", id))));
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public ModelAndView getUserCreationPage() {
        return new ModelAndView("user_create", "form", new UserCreationForm());
    }

    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String handleUserCreationForm(@Valid @ModelAttribute("form") UserCreationForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user_create";
        }
        try {
            userService.create(form);
        } catch (DataIntegrityViolationException e) { //TODO: Add logging for the exception
            bindingResult.reject("username.exists", "That username is already taken");
            return "user_create";
        }
        return "redirect:/home";//TODO: custom redirect?
    }

}