package com.example.listview;

import java.util.Date;

public class ChatMessage {
    private String text;
    private String timestamp;
    private boolean isSentByMe;

    public ChatMessage(String text,  String timestamp, boolean isSentByMe) {
        this.text = text;
        this.timestamp = timestamp;
        this.isSentByMe = isSentByMe;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTimestamp( String timestamp) {
        this.timestamp = timestamp;
    }

    public void setSentByMe(boolean sentByMe) {
        isSentByMe = sentByMe;
    }

    public String getText() {
        return text;
    }

    public  String getTimestamp() {
        return timestamp;
    }

    public boolean isSentByMe() {
        return isSentByMe;
    }
}

