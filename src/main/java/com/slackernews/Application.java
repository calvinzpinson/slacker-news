package com.slackernews;

import com.slackernews.model.Post;
import com.slackernews.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.URL;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    public CommandLineRunner demo(PostRepository repository) {
        return (args) -> {
            URL url = new URL("https://google.com");
            repository.save(new Post("Sample title", url, "sample content", 1));

            // fetch all posts
            log.info("Posts found with findAll():");
            log.info("-------------------------------");
            for (Post post : repository.findAll()) {
                log.info(post.toString());
            }
            log.info("");

            // fetch an individual post by Id
            Post post = repository.findOne(1);
            log.info("Customer found with findOne(1):");
            log.info("--------------------------------");
            log.info(post.toString());
            log.info("");
        };
    }

}