package com.slackernews.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;

@Entity
public class Comment {
    private Integer id;
    private Post post;
    private Integer parentCommentId;
    private String textContent;
    private Integer points;
    private Timestamp date;
    private User commenter;

    // Necessary for JPA
    protected Comment() {};

    public Comment(User commenter, Post post, String textContent) {
        this.textContent = textContent;
        this.post = post;
        this.commenter = commenter;
        this.parentCommentId = 0;

        // Gets the current time
        this.date = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        this.points = 0;
    }

    @Override
    public String toString() {
        return "COMMENT";
    }

    //region Getters and Setters
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "post")
    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public String getTextContent() {
        return this.textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Timestamp getDate() {
        return this.date;
    }

    public void setDate(Timestamp timestamp) {
        this.date = timestamp;
    }

    @ManyToOne
    @JoinColumn(name = "User_id")
    public User getCommenter() {
        return commenter;
    }

    public void setCommenter(User commenter) {
        this.commenter = commenter;
    }
    //endregion

}
