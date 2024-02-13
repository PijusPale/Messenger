package com.pk.messenger.dto;

import java.util.Date;

public class MessageRequestDTO {
    private String text;
    private Date date;
    private Long userId;

    public String getText() {
        return text;
    }

    public Long getUserId() {
        return userId;
    }

    public Date getDate() {
        return date;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
