package com.sportx.team7.chat.service;

import com.sportx.team7.chat.model.EventMessage;
import com.sportx.team7.chat.repository.EventMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventMessageService {

    @Autowired
    private EventMessageRepository repository;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * Persist a new EventMessage to MongoDB, then publish it to all WebSocket subscribers
     * of the "/topic/event.{eventName}" destination.
     *
     * @param message the EventMessage to save
     * @return the saved EventMessage, complete with generated {@code id} and {@code timestamp}
     */
    public EventMessage saveEventMessage(EventMessage message) {
        EventMessage saved = repository.save(message);
        messagingTemplate.convertAndSend("/topic/event." + saved.getEventName(), saved);
        return saved;
    }

    /**
     * Retrieve the full chat history for the given event, ordered oldest→newest.
     *
     * @param eventName the name of the event whose messages to fetch
     * @return a List of EventMessage objects sorted by timestamp ascending
     */
    public List<EventMessage> getEventMessages(String eventName) {
        return repository.findByEventNameOrderByTimestampAsc(eventName);
    }
}
