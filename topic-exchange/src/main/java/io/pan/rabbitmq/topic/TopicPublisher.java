package io.pan.rabbitmq.topic;

import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class TopicPublisher {

  private final Channel channel;

  public TopicPublisher(Channel channel) {
    this.channel = channel;
  }

  public void start() {
    Stream.iterate(1, i -> i + 1).forEach(this::publish);
  }

  private void publish(int i) {
    final String message = "Message " + i;
    try {
      channel.basicPublish("Consoles-topic", "xbox.playstation.switch", null, message.getBytes());
      System.out.printf("Published: %s%n", message);
      TimeUnit.SECONDS.sleep(new Random().nextInt(10));
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }
}
