package com.slackernews.service.Implementation;

import com.slackernews.model.Post;
import com.slackernews.model.PostCreationForm;
import com.slackernews.model.User;
import com.slackernews.repository.IPostRepository;
import com.slackernews.service.Interface.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
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

    @Override
    public Post create(PostCreationForm form, User user) throws MalformedURLException {
        String title = form.getTitle();

        URL url = new URL("https://google.com");

        String text = form.getText();

        Post post = new Post(title, url, text, user);
        post = postRepository.save(post);

        if (form.getUrl().isEmpty()) {
            String urlString = "http://localhost:8080/post/" + post.getId().toString(); //TODO: get url stem from environment variable
            url = new URL(urlString);
            post.setURL(url);
        }

        return postRepository.save(post);
    }

    @Override
    public Post updatePostInfo(Post post) {
        return postRepository.save(post);
    }
}
