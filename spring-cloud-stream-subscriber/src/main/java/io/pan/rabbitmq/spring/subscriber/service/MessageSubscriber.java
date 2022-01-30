package io.pan.rabbitmq.spring.subscriber.service;

import io.pan.rabbitmq.spring.subscriber.config.InputMessageChannel;
import io.pan.rabbitmq.spring.subscriber.dto.Message;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MessageSubscriber {

  @StreamListener(InputMessageChannel.INPUT)
  public void handleMessage(Message message) {
    System.out.println(LocalDateTime.now() + " Receive: " + message);
  }

}
