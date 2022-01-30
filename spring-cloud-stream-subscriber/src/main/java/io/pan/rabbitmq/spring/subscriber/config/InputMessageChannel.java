package io.pan.rabbitmq.spring.subscriber.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface InputMessageChannel {

  String INPUT = "message-input";

  @Input(INPUT)
  SubscribableChannel input();

}
