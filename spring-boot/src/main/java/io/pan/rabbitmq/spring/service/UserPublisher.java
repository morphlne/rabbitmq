package io.pan.rabbitmq.spring.service;

import io.pan.rabbitmq.spring.dto.User;
import io.pan.rabbitmq.spring.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

@Service
@AllArgsConstructor
public class UserPublisher {

  private final RabbitTemplate rabbitTemplate;

  public void publish(UserDTO dto) {

    final User user = new User(dto.getName());

    //'AMQP default' exchange, routing key = queue name
    rabbitTemplate.convertAndSend("Default", user);

    rabbitTemplate.convertAndSend("Direct-exchange", "specific-queue-key", user);

    rabbitTemplate.convertAndSend("Fanout-exchange", "", user);

    rabbitTemplate.convertAndSend("Topic-exchange", "dots.separated.routing", user);

    rabbitTemplate.send("Headers-exchange", "", toMessage(user));
  }

  private Message toMessage(User user) {

    try (final ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
         final ObjectOutput output = new ObjectOutputStream(byteOutput)) {

      output.writeObject(user);
      output.flush();

      final byte[] byteMessage = byteOutput.toByteArray();

      return MessageBuilder.withBody(byteMessage)
          .setHeader("header1", "specific-value1")
          .setHeader("header2", "specific-value2")
          .build();

    } catch (IOException cause) {
      throw new RuntimeException(cause);
    }
  }
}
