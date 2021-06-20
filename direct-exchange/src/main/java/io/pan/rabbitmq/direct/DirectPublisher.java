package io.pan.rabbitmq.direct;

import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class DirectPublisher {

  private final Channel channel;
  private final String routingKey;

  public DirectPublisher(Channel channel, String routingKey) {
    this.channel = channel;
    this.routingKey = routingKey;
  }

  public void start() {
    Stream.iterate(1, i -> i + 1).forEach(this::publish);
  }

  private void publish(int i) {
    final String message = "Message " + i;
    try {
      channel.basicPublish("Consoles-direct", routingKey, null, message.getBytes());
      System.out.printf("Published with routing key %s: %s%n", routingKey, message);
      TimeUnit.SECONDS.sleep(new Random().nextInt(10));
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }
}
