package io.pan.rabbitmq.spring.subscriber.webflux.config;

import io.pan.rabbitmq.spring.subscriber.webflux.dto.Event;
import io.pan.rabbitmq.spring.subscriber.webflux.service.InputEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;

@Configuration
public class StreamConfig {

    private final InputEventHandler manualAcknowledgeHandler;
    private final InputEventHandler autoAcknowledgeHandler;

    public StreamConfig(
            InputEventHandler manualAcknowledgeHandler,
            InputEventHandler autoAcknowledgeHandler
    ) {
        this.manualAcknowledgeHandler = manualAcknowledgeHandler;
        this.autoAcknowledgeHandler = autoAcknowledgeHandler;
    }

    @Bean
    public Consumer<Flux<Message<Event>>> inputManual() {
        return manualAcknowledgeHandler::handleInputEvent;
    }

    @Bean
    public Consumer<Flux<Message<Event>>> inputAuto() {
        return autoAcknowledgeHandler::handleInputEvent;
    }

}
