package io.pan.rabbitmq.spring.subscriber.webflux.service;

import com.rabbitmq.client.Channel;
import io.pan.rabbitmq.spring.subscriber.webflux.dto.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.IOException;

@Service
public class ManualAcknowledgeEvent implements InputEventService {

    private final Logger logger = LoggerFactory.getLogger(ManualAcknowledgeEvent.class);

    @Override
    public void handleInputEvent(Flux<Message<Event>> eventFlux) {
        eventFlux
                .doOnNext(this::logEvent)
                .doOnNext(this::handleEvent)
                .subscribe();
    }

    private void logEvent(Message<Event> event) {
        logger.info(
                "Input event \n Headers: {} \n Payload: {}",
                event.getHeaders(),
                event.getPayload()
        );
    }

    private void handleEvent(Message<Event> event) {
        if (event.getPayload().content() != null) {
            ackEvent(event);
        } else {
            nackEvent(event);
        }
    }

    private void ackEvent(Message<Event> event) {

        var payload = event.getPayload();
        logger.info("Acknowledge event attempt: {}", payload);

        var channel = event.getHeaders().get(AmqpHeaders.CHANNEL, Channel.class);
        var deliveryTag = event.getHeaders().get(AmqpHeaders.DELIVERY_TAG, Long.class);

        try {
            if (channel != null && deliveryTag != null) {
                channel.basicAck(deliveryTag, false);
                logger.info("Acknowledge event success: {}", payload);
            } else {
                logger.warn("Empty channel {} or delivery tag {}: {}", channel, deliveryTag, payload);
                throw new IllegalArgumentException("Empty channel or delivery tag");
            }
        } catch (IllegalArgumentException | IOException cause) {
            logger.warn("Acknowledge event fail: {}", payload, cause);
        }
    }

    private void nackEvent(Message<Event> event) {

        var payload = event.getPayload();
        logger.info("Negative acknowledge event attempt: {}", payload);

        var channel = event.getHeaders().get(AmqpHeaders.CHANNEL, Channel.class);
        var deliveryTag = event.getHeaders().get(AmqpHeaders.DELIVERY_TAG, Long.class);

        try {
            if (channel != null && deliveryTag != null) {
                channel.basicNack(deliveryTag, false, false);
                logger.info("Negative acknowledge event success: {}", payload);
            } else {
                logger.warn("Empty channel {} or delivery tag {}: {}", channel, deliveryTag, payload);
                throw new IllegalArgumentException("Empty channel or delivery tag");
            }
        } catch (IllegalArgumentException | IOException cause) {
            logger.warn("Negative acknowledge event fail: {}", payload, cause);
        }
    }

}
