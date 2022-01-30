package io.pan.rabbitmq.spring.publisher.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OutputMessageChannel {

  @Output("message-output")
  MessageChannel output();

}
