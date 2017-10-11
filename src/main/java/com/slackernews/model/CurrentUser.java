package com.slackernews.model;

import org.springframework.security.core.authority.AuthorityUtils;

//TODO: Refactor to avoid leaking domain object...perhaps reduce data access
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user) {
        super(user.getUsername(), user.getPasswordHash(), AuthorityUtils.createAuthorityList("USER"));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Integer getId() {
        return user.getId();
    }

}
