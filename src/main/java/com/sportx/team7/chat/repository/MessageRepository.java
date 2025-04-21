package com.sportx.team7.chat.repository;

import com.sportx.team7.chat.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import org.springframework.data.mongodb.repository.Query;

public interface MessageRepository extends MongoRepository<Message, String> {

    /**
     * Retrieve the full conversation between two users, sorted by {@code timestamp} ascending.
     * <p>
     * This uses a MongoDB {@code $or} query to find all {@link Message} documents where
     * either:
     * <ul>
     *   <li>{@code senderId} == {@code userId1} and {@code receiverId} == {@code userId2}, or</li>
     *   <li>{@code senderId} == {@code userId2} and {@code receiverId} == {@code userId1}.</li>
     * </ul>
     * </p>
     *
     * @param userId1 the ID of one participant in the conversation
     * @param userId2 the ID of the other participant in the conversation
     * @return a {@link List} of {@link Message} objects for both sides of the conversation,
     *         ordered from oldest to newest
     */
    @Query("{ $or: [ "
            + "{ 'senderId': ?0, 'receiverId': ?1 }, "
            + "{ 'senderId': ?1, 'receiverId': ?0 } "
            + "] }")
    List<Message> findConversationBetweenUsers(String userId1, String userId2);

}
