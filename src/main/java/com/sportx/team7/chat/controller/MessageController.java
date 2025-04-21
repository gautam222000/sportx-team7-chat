package com.sportx.team7.chat.controller;

import com.sportx.team7.chat.model.Message;
import com.sportx.team7.chat.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    /**
     * Register a new chat message.
     *
     * @param message the incoming Message payload
     * @return 201 Created with the saved Message (including generated ID and timestamp)
     */
    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        Message saved = messageService.saveMessage(message);
        return ResponseEntity.status(201).body(saved);
    }

    /**
     * Retrieve chat history between two users.
     *
     * @param senderId   the ID of the user who sent messages
     * @param receiverId the ID of the user who received messages
     * @return 200 OK with the list of messages sorted by timestamp ascending
     */
    @GetMapping
    public ResponseEntity<List<Message>> getMessages(
            @RequestParam String senderId,
            @RequestParam String receiverId) {
        List<Message> response = messageService.getMessages(senderId, receiverId);
        return ResponseEntity.ok(response);
    }
}
