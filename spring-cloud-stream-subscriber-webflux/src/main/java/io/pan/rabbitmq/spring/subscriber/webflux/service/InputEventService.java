package io.pan.rabbitmq.spring.subscriber.webflux.service;

import io.pan.rabbitmq.spring.subscriber.webflux.dto.Event;
import org.springframework.messaging.Message;
import reactor.core.publisher.Flux;

public interface InputEventService {

    void handleInputEvent(Flux<Message<Event>> eventFlux);

}
