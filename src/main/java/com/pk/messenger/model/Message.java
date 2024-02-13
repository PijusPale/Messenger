package com.pk.messenger.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;
import java.util.Objects;

public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;
    private String text;
    private Date date;
    private Long userId;
    Message(){}
    Message(String text, Date date, Long userId){
        this.text = text;
        this.date = date;
        this.userId = userId;
    }

    public Long getId() {
        return Id;
    }
    public String getText() {
        return this.text;
    }
    public Date getDate() {
        return this.date;
    }
    public Long getUserId() {
        return this.userId;
    }
    public void setId(Long id) {
        Id = id;
    }
    public void setText(String text) {
        this.text = text;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(getId(), message.getId()) && Objects.equals(getText(), message.getText()) && Objects.equals(getDate(), message.getDate()) && Objects.equals(getUserId(), message.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getText(), getDate(), getUserId());
    }

    @Override
    public String toString() {
        return "Message{" +
                "Id=" + Id +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", userId=" + userId +
                '}';
    }
}
