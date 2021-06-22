package io.pan.rabbitmq.headers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;

public class Consumer {

  private final Channel channel;
  private final String queueName;

  public Consumer(Channel channel, String queueName) {
    this.channel = channel;
    this.queueName = queueName;
  }

  public void start() {
    DeliverCallback deliverCallback =
        (consumerTag, message) -> System.out.printf("Received by %s: %s%n", queueName, new String(message.getBody()));
    try {
      channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
