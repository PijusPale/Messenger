package com.pk.messenger.model;

import java.util.Date;

public class MyUserStats {
    private MyUser myUser;
    private int messageCount;
    private Date firstMessageDate;
    private Date lastMessageDate;
    private double averageMessageLength;
    private String lastMessageText;

    public MyUserStats(MyUser myUser, int messageCount,
                       Date firstMessageDate, Date lastMessageDate,
                       double averageMessageLength, String lastMessageText){
        this.myUser = myUser;
        this.messageCount = messageCount;
        this.firstMessageDate = firstMessageDate;
        this.lastMessageDate = lastMessageDate;
        this.averageMessageLength = averageMessageLength;
        this.lastMessageText = lastMessageText;
    }

    public MyUser getMyUser() {
        return myUser;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public Date getFirstMessageDate() {
        return firstMessageDate;
    }

    public Date getLastMessageDate() {
        return lastMessageDate;
    }

    public double getAverageMessageLength() {
        return averageMessageLength;
    }

    public String getLastMessageText() {
        return lastMessageText;
    }
}
