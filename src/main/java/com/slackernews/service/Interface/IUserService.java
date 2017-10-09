package com.slackernews.service.Interface;

import com.slackernews.model.User;
import com.slackernews.model.UserCreationForm;

import java.util.Optional;

public interface IUserService {
    Optional<User> getUserById(int id);

    Optional<User> getUserByEmail(String email);

    Optional<User> getUserByUsername(String username);

    User create(UserCreationForm form);
}
