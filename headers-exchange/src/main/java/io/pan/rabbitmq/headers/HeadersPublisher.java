package io.pan.rabbitmq.headers;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HeadersPublisher {

  private final Channel channel;
  private final BasicProperties properties;
  private final String name;

  public HeadersPublisher(Channel channel, BasicProperties properties, String name) {
    this.channel = channel;
    this.properties = properties;
    this.name = name;
  }

  public HeadersPublisher(Channel channel, String firstHeader, String secondHeader, String name) {
    this(
        channel,
        new BasicProperties().builder().headers(
            Stream.of(new String[][]{
                {"tag1", firstHeader},
                {"tag2", secondHeader},
            }).collect(Collectors.toMap(data -> data[0], data -> data[1]))
        ).build(),
        name
    );
  }

  public void start() {
    Stream.iterate(1, i -> i + 1).forEach(this::publish);
  }

  private void publish(int i) {
    final String message = "Message " + i;
    try {
      channel.basicPublish("Consoles-headers", "", properties, message.getBytes());
      System.out.printf("Published %s: %s%n", name, message);
      TimeUnit.SECONDS.sleep(new Random().nextInt(10));
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }
}
