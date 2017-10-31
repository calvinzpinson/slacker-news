package com.slackernews.service.Interface;

import com.slackernews.model.Post;
import com.slackernews.model.PostCreationForm;
import com.slackernews.model.User;

import java.net.MalformedURLException;
import java.security.Principal;
import java.util.Optional;

public interface IPostService {
    Optional<Post> getPostById(int id);
    Post create(PostCreationForm form, User user) throws MalformedURLException;
}
