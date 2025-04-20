package com.sportx.team7.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mongodb.client.MongoClient;

@RestController
public class HealthController {

    @Autowired
    private MongoClient mongoClient;

    @GetMapping("/health")
    public String health() {
        // ping the DB
        mongoClient.getDatabase("sportx").runCommand(new org.bson.Document("ping", 1));
        return "OK";
    }
}
