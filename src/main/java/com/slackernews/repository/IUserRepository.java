package com.slackernews.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slackernews.model.User;

public interface IUserRepository extends JpaRepository<User, Integer> {
    User findById(Integer id);
}
