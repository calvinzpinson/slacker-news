package com.slackernews.service.Implementation;

import com.slackernews.model.Post;
import com.slackernews.repository.IPostRepository;
import com.slackernews.service.Interface.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService implements IPostService {

    private IPostRepository postRepository;

    @Autowired
    public PostService(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Optional<Post> getPostById(int id) {
        return Optional.ofNullable(postRepository.findOne(id));
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
