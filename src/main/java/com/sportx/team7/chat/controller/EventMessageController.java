package com.sportx.team7.chat.controller;

import com.sportx.team7.chat.model.EventMessage;
import com.sportx.team7.chat.service.EventMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for event (group) chat operations.
 * <p>
 * Exposes endpoints to post a new group message and to retrieve all messages
 * for a given event name.
 * </p>
 */
@RestController
@RequestMapping("/api/messages/events")
public class EventMessageController {

    @Autowired
    private EventMessageService service;

    /**
     * Fetch the chat history for the specified event.
     * <p>
     * Accepts the event name as a query parameter:
     * GET /api/messages/events?eventName=EventOne
     * </p>
     *
     * @param eventName the name of the event whose messages to retrieve
     * @return 200 OK with the list of EventMessage objects ordered by timestamp ascending
     */
    @GetMapping
    public ResponseEntity<List<EventMessage>> getMessages(@RequestParam String eventName) {
        List<EventMessage> history = service.getEventMessages(eventName);
        return ResponseEntity.ok(history);
    }

    /**
     * Post a new message to the specified event chat.
     * <p>
     * The JSON body must include at least:
     * <ul>
     *   <li>eventName</li>
     *   <li>eventUsers</li>
     *   <li>senderId</li>
     *   <li>senderName</li>
     *   <li>message</li>
     * </ul>
     * Timestamp will be set server‑side.
     * </p>
     *
     * Example:
     * POST /api/messages/events
     * Body:
     * {
     *   "eventName": "EventOne",
     *   "eventUsers": ["userone","usertwo"],
     *   "senderId": "userone",
     *   "senderName": "User One",
     *   "message": "Hello all!"
     * }
     *
     * @param payload the EventMessage payload (including eventName in the body)
     * @return 201 Created with the saved EventMessage (including generated ID and timestamp)
     */
    @PostMapping
    public ResponseEntity<EventMessage> postMessage(@RequestBody EventMessage payload) {
        // ensure payload.eventName is set
        String eventName = payload.getEventName();
        if (eventName == null || eventName.isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        EventMessage saved = service.saveEventMessage(payload);
        return ResponseEntity.status(201).body(saved);
    }
}
