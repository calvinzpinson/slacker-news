package com.slackernews.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

import java.net.URL;
import java.util.Calendar;

@Entity // This tells Hibernate to make a table out of this class
public class Post {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String title;
    private URL url;
    private String textContent;
    private Integer points;
    private Integer userId;
    private Timestamp postDate;

    // Necessary for JPA
    protected Post() {};

    public Post(String title, URL url, String textContent, int userId) {
        this.title = title;
        this.url = url;
        this.textContent = textContent;
        this.userId = userId;

        this.points = 0;
        // Gets the current time
        this.postDate = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
    }

    @Override
    public String toString() {
        return "BEANS YO";
    }


}