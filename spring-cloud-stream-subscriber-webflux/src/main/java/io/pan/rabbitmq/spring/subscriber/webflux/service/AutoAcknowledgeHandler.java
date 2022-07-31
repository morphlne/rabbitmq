package io.pan.rabbitmq.spring.subscriber.webflux.service;

import io.pan.rabbitmq.spring.subscriber.webflux.dto.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class AutoAcknowledgeHandler implements InputEventHandler {

    private final Logger logger = LoggerFactory.getLogger(AutoAcknowledgeHandler.class);

    @Override
    public void handleInputEvent(Flux<Message<Event>> events) {
        events
                .doOnNext(this::logEvent)
                .subscribe();

    }

    private void logEvent(Message<Event> event) {
        logger.info(
                "Input event \n Headers: {} \n Payload: {}",
                event.getHeaders(),
                event.getPayload()
        );
    }

}
