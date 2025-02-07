package com.denlab.setrr.lead.events;

import org.springframework.stereotype.Component;

@Component
public class LeadRouteCreatedMessageConsumer {

//    @KafkaListener(topics = "my-topic", groupId = "setrr-k-group")
    public void listen(String message) {
        System.out.println(" ------------------ Received message: " + message);
    }


//    @KafkaListener(topics = "quickstart-events", groupId = "setrr-k-group")
    public void listenquick(String message) {
        System.out.println(" ------------------ Received message: " + message);
    }
}
