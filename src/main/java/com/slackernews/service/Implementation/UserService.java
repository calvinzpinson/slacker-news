package com.slackernews.service.Implementation;

import com.slackernews.model.User;
import com.slackernews.model.UserCreationForm;
import com.slackernews.repository.IUserRepository;
import com.slackernews.service.Interface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService{

    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserById(int id) {
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findOneByUsername(username);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findOneByEmail(email);
    }

    @Override
    public User create(UserCreationForm form) {
        String username = form.getUsername();
        String email = form.getEmail();
        String passwordHash = new BCryptPasswordEncoder().encode(form.getPassword());

        User user = new User(username, email, passwordHash);

        return userRepository.save(user);
    }
}
