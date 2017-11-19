package com.slackernews.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class CommentCreationForm {

    @NotNull
    private Integer id;

    @NotEmpty
    private String text = "";

    //region Getters and Setters

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //endregion
}