package com.codingdojo.ninjagold.models;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

public class Message {
    private String text;
    private String result;
    private LocalDateTime time;

    public Message(String text, String result) {
        this.text = text;
        this.result = result;
        this.time = new LocalDateTime();
    }

    public Message() {
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getText() {
        return text;
    }

    public String getResult() {
        return result;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
