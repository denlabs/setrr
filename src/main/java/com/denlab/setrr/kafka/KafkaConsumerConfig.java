package com.denlab.setrr.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.requireNonNull;

@Configuration
public class KafkaConsumerConfig {

    @Value("${env.kafka.bootstrap.servers.config:kafka-service.kafka.svc.cluster.local:9092}")
    private String bootstrapServersConfig = "kafka-service.kafka.svc.cluster.local:9092";

    @Value("${env.kafka.group.id.config:setrr-k-group}")
    private String groupIdConfig = "setrr-k-group";

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, requireNonNull(bootstrapServersConfig));
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, requireNonNull(groupIdConfig));
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(configProps);
    }

//    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}

