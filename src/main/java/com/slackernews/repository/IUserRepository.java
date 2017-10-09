package com.slackernews.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.slackernews.model.User;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {
    Optional<User> findOneByEmail(String email);
    Optional<User> findOneByUsername(String username);
}
