package io.pan.rabbitmq.spring.service.consumer;

import io.pan.rabbitmq.spring.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

@Slf4j
@Service
public class UserHeadersQueueConsumer {

  @RabbitListener(queues = "Headers-queue")
  public void getMessage(byte[] message) {

    try (final ByteArrayInputStream byteInput = new ByteArrayInputStream(message);
         final ObjectInput input = new ObjectInputStream(byteInput)) {

      final User user = (User) input.readObject();

      log.info("Received user: id={}, name={}", user.getId(), user.getName());

    } catch (IOException | ClassNotFoundException cause) {
      throw new RuntimeException(cause);
    }

  }
}
