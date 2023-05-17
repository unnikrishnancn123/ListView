package com.example.listview;


public class Message {


    private String from, message,date;

    public Message(String from, String message, String date) {
        this.date = date;
        this.from = from;
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }
}
