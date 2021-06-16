package io.pan.rabbitmq.amqp.client;

import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Producer {

  private final Channel channel;
  private final String queueName;

  public Producer(Channel channel, String queueName) {
    this.channel = channel;
    this.queueName = queueName;
  }

  public void start() {
    Stream.iterate(1, i -> i + 1).forEach(this::publish);
  }

  private void publish(int i) {
    final String message = "Message " + i;
    try {
      channel.basicPublish("", queueName, null, message.getBytes());
      System.out.println("Published: " + message);
      TimeUnit.SECONDS.sleep(1);
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }
}
