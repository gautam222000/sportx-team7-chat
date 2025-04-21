package com.sportx.team7.chat.service;

import com.sportx.team7.chat.model.Message;
import com.sportx.team7.chat.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import java.time.Instant;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    /**
     * Persist a new Message. Ensures timestamp is set to now.
     *
     * @param message the message to save
     * @return the saved Message with generated ID and timestamp
     */
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    /**
     * Fetch the chat history between two users.
     * <p>
     * Uses the repository’s method to retrieve messages where
     * senderId == {@code senderId} and receiverId == {@code receiverId},
     * ordered by timestamp ascending.
     * </p>
     *
     * @param senderId   the ID of the user who sent the messages
     * @param receiverId the ID of the user who received the messages
     * @return a list of {@link Message} sorted by timestamp (oldest first)
     */
    public List<Message> getMessages(String senderId, String receiverId) {
        return messageRepository.findBySenderIdAndReceiverIdOrderByTimestampAsc(senderId, receiverId);
    }
}
