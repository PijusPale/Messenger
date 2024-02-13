package com.pk.messenger.dto;

import com.pk.messenger.model.MyUser;

import java.util.Date;

public class MessageRequestDTO {
    private String text;
    private Date date;
    private Long userId;
    public MessageRequestDTO(String text, Date date, Long userId){
        this.text = text;
        this.date = date;
        this.userId = userId;
    }

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
