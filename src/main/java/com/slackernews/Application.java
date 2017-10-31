package com.slackernews;

import com.slackernews.model.Post;
import com.slackernews.model.User;
import com.slackernews.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.URL;

@SpringBootApplication
public class Application {

    public static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(IPostRepository postRepository, IUserRepository userRepository) {
        return (args) -> {
            URL url = new URL("https://google.com");
            User user = new User("NAME", "EMAIL", "PASSWORD");
            userRepository.save(user);
            postRepository.save(new Post("Sample title", url, "sample content", user));

            // fetch all posts
            log.info("Posts found with findAll():");
            log.info("-------------------------------");
            for (Post post : postRepository.findAll()) {
                log.info(post.toString());
            }
            log.info("");

            // fetch an individual post by Id
            Post post = postRepository.findOne(1);
            log.info("Customer found with findOne(1):");
            log.info("--------------------------------");
            log.info(post.toString());
            log.info("");
        };
    }

}