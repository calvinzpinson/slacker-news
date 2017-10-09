package com.slackernews.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.net.URL;
import java.util.Calendar;

@Entity // This tells Hibernate to make a table out of this class
public class Post {

    private Integer id;
    private String title;
    private URL url;
    private String textContent;
    private Integer points;
    private Timestamp postDate;
    private User poster;

    // Necessary for JPA
    protected Post() {};

    public Post(String title, URL url, String textContent, User poster) {
        this.title = title;
        this.url = url;
        this.textContent = textContent;
        this.poster = poster;

        // Gets the current time
        this.postDate = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        this.points = 0;
    }


    @Override
    public String toString() {
        return "POST";
    }

    //region Getters and Setters
    @Id // indicates primary key
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public URL getURL() {
        return url;
    }

    public void setURL(URL url) {
        this.url = url;
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

    @ManyToOne
    @JoinColumn(name = "User_id")
    public User getPoster() {
        return poster;
    }

    public void setPoster(User poster) {
        this.poster = poster;
    }
    //endregion


}