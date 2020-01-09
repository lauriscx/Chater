package com.gym.chater.Data;

public class Message {
    String message;
    boolean readed;
    String sender;
    String receiver;
    String time;
    String emoji;

    public Message() {
        this.message = "message";
        this.readed = false;
        this.sender = "sender";
        this.receiver = "receiver";
        this.time = "time";
        this.emoji = "emoji";
    }

    public Message(String message, boolean readed, String sender, String receiver, String time, String emoji) {
        this.message = message;
        this.readed = readed;
        this.sender = sender;
        this.receiver = receiver;
        this.time = time;
        this.emoji = emoji;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isReaded() {
        return readed;
    }

    public void setReaded(boolean readed) {
        this.readed = readed;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEmoji() {
        return emoji;
    }

    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }
}
