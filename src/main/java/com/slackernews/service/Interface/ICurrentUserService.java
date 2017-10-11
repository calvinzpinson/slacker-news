package com.slackernews.service.Interface;

import com.slackernews.model.CurrentUser;

public interface ICurrentUserService {
    boolean canAccessUser(CurrentUser currentUser, Long userId);
}
