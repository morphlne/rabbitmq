package io.pan.rabbitmq.spring.subscriber.webflux.config;

import io.pan.rabbitmq.spring.subscriber.webflux.dto.Event;
import io.pan.rabbitmq.spring.subscriber.webflux.service.InputEventService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;

@Configuration
public class StreamConfig {

    private final InputEventService manualAcknowledgeEvent;

    public StreamConfig(
            @Qualifier("manualAcknowledgeEvent") InputEventService manualAcknowledgeEvent
    ) {
        this.manualAcknowledgeEvent = manualAcknowledgeEvent;
    }

    @Bean
    public Consumer<Flux<Message<Event>>> inputManual() {
        return manualAcknowledgeEvent::handleInputEvent;
    }

}
