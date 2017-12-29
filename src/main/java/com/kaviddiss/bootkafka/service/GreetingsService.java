package com.kaviddiss.bootkafka.service;

import com.kaviddiss.bootkafka.model.Greetings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class GreetingsService {
    private static final String KAFKA_TOPICS_DEMO_CONFIG = "${kafka.topics.demo}";

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final KafkaTemplate<String, Greetings> eventKafkaTemplate;
    private final String demoTopic;

    public GreetingsService(KafkaTemplate<String, Greetings> eventKafkaTemplate,
                            @Value(KAFKA_TOPICS_DEMO_CONFIG) String demoTopic) {
        this.eventKafkaTemplate = eventKafkaTemplate;
        this.demoTopic = demoTopic;
    }

    public void sendGreeting(final Greetings greetings) {
        log.info("Sending greetings {}", greetings);

        ListenableFuture<SendResult<String, Greetings>> result = eventKafkaTemplate.send(demoTopic, greetings);
        result.addCallback(new ListenableFutureCallback<SendResult<String, Greetings>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("Failed to send greetings {}", greetings, ex);
            }

            @Override
            public void onSuccess(SendResult<String, Greetings> result) {
                log.info("Successfully sent greetings {}", greetings);
            }
        });
    }

    @KafkaListener(topics = KAFKA_TOPICS_DEMO_CONFIG, containerFactory = "kafkaJsonListenerContainerFactory")
    public void eventListener(Greetings greetings) {
        log.info("Received greetings: {}", greetings);
    }
}
