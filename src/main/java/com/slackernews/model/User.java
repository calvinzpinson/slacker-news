package com.slackernews.model;

import javax.persistence.*;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class User {
    private Integer id;
    private String username;
    private String password;
    private Set<Post> posts;
    private Set<Comment> comments;

    // Necessary for JPA
    protected User() {};

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "poster", cascade = CascadeType.ALL)
    public Set<Post> getPosts() {
        return posts;
    }

    @OneToMany(mappedBy = "commenter", cascade = CascadeType.ALL)
    public Set<Comment> getComments() {
        return comments;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "USER";
    }

}
