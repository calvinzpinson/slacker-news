package com.slackernews.model;

import org.hibernate.validator.constraints.NotEmpty;

public class PostCreationForm {
    @NotEmpty
    private String title = "";

    @NotEmpty
    private String url = "";

    @NotEmpty
    private String text = "";

    //region Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    //endregion
}