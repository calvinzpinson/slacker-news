package com.slackernews.service.Implementation;

import com.slackernews.model.CurrentUser;
import com.slackernews.service.Interface.ICurrentUserService;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService implements ICurrentUserService {

    @Override
    public boolean canAccessUser(CurrentUser currentUser, Long userId) {
        return currentUser != null && currentUser.getId().equals(userId);
    }
}