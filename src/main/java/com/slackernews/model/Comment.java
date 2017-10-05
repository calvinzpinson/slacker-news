package com.slackernews.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;

@Entity
public class Comment {
    private Integer id;
    private Integer parentCommentId;
    private String textContent;
    private Integer points;
    private Timestamp date;
    private User commenter;

    // Necessary for JPA
    protected Comment() {};

    public Comment(User commenter, Integer parentCommentId, String textContent) {
        this.textContent = textContent;
        this.commenter = commenter;
        this.parentCommentId = parentCommentId;

        // Gets the current time
        this.date = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        this.points = 0;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "User_id")
    public User getCommenter() {
        return commenter;
    }

    public void setCommenter(User commenter) {
        this.commenter = commenter;
    }

    @Override
    public String toString() {
        return "COMMENT";
    }

}
