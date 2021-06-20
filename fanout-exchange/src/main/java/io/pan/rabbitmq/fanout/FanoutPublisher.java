package io.pan.rabbitmq.fanout;

import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class FanoutPublisher {

  private final Channel channel;

  public FanoutPublisher(Channel channel) {
    this.channel = channel;
  }

  public void start() {
    Stream.iterate(1, i -> i + 1).forEach(this::publish);
  }

  private void publish(int i) {
    final String message = "Message " + i;
    try {
      channel.basicPublish("Consoles-fanout", "", null, message.getBytes());
      System.out.printf("Published: %s%n", message);
      TimeUnit.SECONDS.sleep(new Random().nextInt(10));
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }
}
