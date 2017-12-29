package com.kaviddiss.bootkafka.config;

import com.kaviddiss.bootkafka.model.Greetings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

/**
 * Configures a <code>KafkaTemplate</code> for sending and a <code>KafkaListenerContainerFactory</code> for receiving Kafka messages.
 * Config properties come from the <code>application.yaml</code> file and will be mapped to <code>KafkaProperties</code>.
 *
 * @see org.springframework.boot.autoconfigure.kafka.KafkaProperties
 * @author davidk
 */
@Configuration
@EnableKafka
public class KafkaConfig {
    @Bean
    public KafkaTemplate<String, Greetings> kafkaTemplate(ProducerFactory kafkaProducerFactory) {
        return new KafkaTemplate<>(kafkaProducerFactory);
    }

    @Bean
    public KafkaListenerContainerFactory<?> kafkaJsonListenerContainerFactory(ConsumerFactory<Integer, String> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setMessageConverter(new StringJsonMessageConverter());
        return factory;
    }
}
