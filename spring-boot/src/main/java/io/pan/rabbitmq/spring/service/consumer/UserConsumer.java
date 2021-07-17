package io.pan.rabbitmq.spring.service.consumer;

import io.pan.rabbitmq.spring.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserConsumer {

  //Default, Direct, Fanout, Topic
  @RabbitListener(queues = "Default")
  public void getMessage(User user) {

    log.info("Received user: id={}, name={}", user.getId(), user.getName());

  }
}
