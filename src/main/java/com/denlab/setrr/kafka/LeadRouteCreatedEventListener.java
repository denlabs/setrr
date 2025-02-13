package com.denlab.setrr.kafka;

import io.swagger.v3.oas.annotations.servers.Server;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class LeadRouteCreatedEventListener {
    @KafkaListener(topics = "my-topic", groupId = "my-group")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }

    @KafkaListener(topics = "my-topic", groupId = "my-group")
    public void listenWithRecord(ConsumerRecord<String, String> record) {
        System.out.println("Received key: " + record.key() + ", value: " + record.value());
    }
}
