package com.sportx.team7.chat.repository;

import com.sportx.team7.chat.model.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {

    /**
     * Retrieve all {@link Message} documents where:
     * <ul>
     *   <li>{@code senderId} matches the given {@code senderId}, and</li>
     *   <li>{@code receiverId} matches the given {@code receiverId}.</li>
     * </ul>
     * <p>
     * Results are sorted by the {@code timestamp} field in ascending order,
     * so older messages appear first.
     * </p>
     *
     * @param senderId   the ID of the user who sent the messages
     * @param receiverId the ID of the user who received the messages
     * @return a {@link List} of {@link Message} objects matching the criteria,
     *         ordered by timestamp ascending
     */
    List<Message> findBySenderIdAndReceiverIdOrderByTimestampAsc(String senderId, String receiverId);

}
