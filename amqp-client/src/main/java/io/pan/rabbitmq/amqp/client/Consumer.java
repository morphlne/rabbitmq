package io.pan.rabbitmq.amqp.client;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;

public class Consumer {

  private final Channel channel;
  private final String queueName;
  private final String consumerName;

  public Consumer(Channel channel, String queueName, String name) {
    this.channel = channel;
    this.queueName = queueName;
    this.consumerName = name;
  }

  public void start() {
    DeliverCallback deliverCallback =
        (consumerTag, message) -> System.out.printf("Received by %s: %s%n", consumerName, new String(message.getBody()));
    try {
      channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
