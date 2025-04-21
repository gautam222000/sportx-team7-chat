package com.sportx.team7.chat;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocket implements WebSocketMessageBrokerConfigurer {

    /**
     * Register the STOMP over WebSocket endpoint that clients will use to connect.
     * <ul>
     *   <li>Endpoint path: <code>/ws</code> (WebSocket handshake URL).</li>
     *   <li>Allowed origins: all (<code>*</code>), permitting cross‑origin requests.</li>
     *   <li>SockJS fallback: enabled to support browsers without native WebSocket.</li>
     * </ul>
     *
     * @param registry the registry to which STOMP endpoints are added
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/ws")
                .setAllowedOriginPatterns("*")
                .withSockJS();
    }

    /**
     * Configure the message broker for routing messages between server and clients.
     * <ul>
     *   <li>Enables a simple, in‑memory broker for destinations prefixed with
     *       <code>/topic</code>, which is used for server→client broadcasts.</li>
     *   <li>Sets the application destination prefix to <code>/app</code>, which is
     *       reserved for messages sent from clients to @MessageMapping methods
     *       (not currently used).</li>
     * </ul>
     *
     * @param registry the MessageBrokerRegistry used to configure broker options
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }
}
