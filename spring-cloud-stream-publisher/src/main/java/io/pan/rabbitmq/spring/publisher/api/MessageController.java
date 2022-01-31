package io.pan.rabbitmq.spring.publisher.api;

import io.pan.rabbitmq.spring.publisher.config.OutputMessageChannel;
import io.pan.rabbitmq.spring.publisher.dto.Message;
import lombok.AllArgsConstructor;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
public class MessageController {

  private final OutputMessageChannel output;

  @GetMapping(value = "/send")
  public void sendMessage() {
    System.out.println(LocalDateTime.now() + " Publish");
    output.output().send(
        MessageBuilder
            .withPayload(new Message("Message from publisher"))
            .build()
    );
  }

  @GetMapping(value = "/send/dlq")
  public void sendDlqMessage() {
    System.out.println(LocalDateTime.now() + " Publish DLQ");
    output.output().send(
        MessageBuilder
            .withPayload(new Message("Message DLQ from publisher"))
            .build()
    );
  }

}
