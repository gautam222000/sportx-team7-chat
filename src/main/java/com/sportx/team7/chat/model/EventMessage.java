package com.sportx.team7.chat.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

/**
 * Represents a chat message sent within an event (group) chat.
 * Stored in the "event_messages" collection.
 */
@Document(collection = "event_messages")
public class EventMessage {

    @Id
    private String id;

    /**
     * The name of the event this message belongs to.
     */
    private String eventName;

    /**
     * The list of usernames (or IDs) of all participants in the event.
     */
    private List<String> eventUsers;

    /**
     * ID of the user who sent the message.
     */
    private String senderId;

    /**
     * Display name of the user who sent the message.
     */
    private String senderName;

    /**
     * The text content of the message.
     */
    private String message;

    /**
     * Timestamp when the message was created.
     */
    private Instant timestamp;

    public EventMessage() {
        this.timestamp = Instant.now();
    }

    public EventMessage(String eventName,
                        List<String> eventUsers,
                        String senderId,
                        String senderName,
                        String message) {
        this.eventName  = eventName;
        this.eventUsers = eventUsers;
        this.senderId   = senderId;
        this.senderName = senderName;
        this.message    = message;
        this.timestamp  = Instant.now();
    }

    // Getters & setters

    public String getId() {
        return id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public List<String> getEventUsers() {
        return eventUsers;
    }

    public void setEventUsers(List<String> eventUsers) {
        this.eventUsers = eventUsers;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
