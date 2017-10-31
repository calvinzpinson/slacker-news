package com.slackernews.service.Implementation;

import com.slackernews.model.CurrentUser;
import com.slackernews.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CurrentUserDetailsService implements UserDetailsService {
    private final UserService userService;
    private static final Logger log = LoggerFactory.getLogger(CurrentUserDetailsService.class);

    @Autowired
    public CurrentUserDetailsService(UserService userService) {
        log.debug("CREATING USER DETAILS SERVICE");
        this.userService = userService;
    }

    @Override
    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Authenticating user with username={}", username.replaceFirst("@.*", "@***"));
        User user = userService.getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
        return new CurrentUser(user);
    }
}
