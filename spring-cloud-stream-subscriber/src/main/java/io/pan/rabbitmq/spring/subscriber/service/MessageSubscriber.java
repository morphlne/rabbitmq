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
    if (message.getMessage().toLowerCase().contains("dlq")) {
      //any error during message handling push it to dlq
      throw new RuntimeException();
    }
    //happy flow
    System.out.println(LocalDateTime.now() + " Receive: " + message);
  }

}
