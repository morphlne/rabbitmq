package io.pan.rabbitmq.headers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

public class PreparedChannel implements Supplier<Channel> {

  private final ConnectionFactory factory;

  public PreparedChannel(ConnectionFactory factory) {
    this.factory = factory;
  }

  public PreparedChannel() {
    this(new ConnectionFactory());
  }

  @Override
  public Channel get() {
    Channel channel = null;
    try {
      Connection connection = factory.newConnection();
      channel = connection.createChannel();
    } catch (IOException | TimeoutException e) {
      e.printStackTrace();
    }

    return channel;
  }
}
