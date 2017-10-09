package com.slackernews.model.Validator;

import com.slackernews.model.UserCreationForm;
import com.slackernews.service.Implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserCreationFormValidator implements Validator {

    private final UserService userService;

    @Autowired
    public UserCreationFormValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserCreationForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserCreationForm form = (UserCreationForm) target;
        validatePasswords(errors, form);
        validateEmail(errors, form);
    }

    private void validatePasswords(Errors errors, UserCreationForm form) {
        if (!form.getPassword().equals(form.getPasswordRepeated())) {
            errors.reject("password.no_match", "Passwords do not match");
        }
    }

    private void validateUsername(Errors errors, UserCreationForm form) {
        if (userService.getUserByUsername(form.getUsername()).isPresent()) {
            errors.reject("username.exists", "Username is already taken");
        }
    }

    //TODO: Allow multiple emails per handle?
    private void validateEmail(Errors errors, UserCreationForm form) {
        if (userService.getUserByEmail(form.getEmail()).isPresent()) {
            errors.reject("email.exists", "User with this email already exists");
        }
    }
}
