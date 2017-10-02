package com.slackernews.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.util.Calendar;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer parentCommentId;
    private String textContent;
    private Integer points;
    private Timestamp date;

    // Necessary for JPA
    protected Comment() {};

    public Comment(Integer userId, Integer parentCommentId, String textContent) {
        this.textContent = textContent;
        this.userId = userId;
        this.parentCommentId = parentCommentId;

        // Gets the current time
        this.date = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        this.points = 0;
    }

    @Override
    public String toString() {
        return "COMMENT";
    }


}
