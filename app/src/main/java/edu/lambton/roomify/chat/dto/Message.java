package edu.lambton.roomify.chat.dto;

public class Message {
    private String text;
    private String senderId;
    private String recipientId;
    private long milliseconds;

    public Message() {
    }

    public Message(String text, String senderId) {
        this.text = text;
        this.senderId = senderId;
    }

    public Message(String text, String senderId, String recipientId, long milliseconds) {
        this(text, senderId);
        this.milliseconds = milliseconds;
        this.recipientId = recipientId;
    }

    public String getText() {
        return text;
    }

    public String getSenderId() {
        return senderId;
    }

    public long getMilliseconds() {
        return milliseconds;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }
}
