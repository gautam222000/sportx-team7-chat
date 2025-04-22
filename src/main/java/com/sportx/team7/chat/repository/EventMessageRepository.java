package com.sportx.team7.chat.repository;

import com.sportx.team7.chat.model.EventMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repository for persisting and retrieving {@link EventMessage} documents.
 */
public interface EventMessageRepository extends MongoRepository<EventMessage, String> {

    /**
     * Fetches all messages for the given event name, ordered by timestamp ascending.
     *
     * @param eventName the name of the event (group chat)
     * @return a list of {@link EventMessage}, oldest first
     */
    List<EventMessage> findByEventNameOrderByTimestampAsc(String eventName);

}
