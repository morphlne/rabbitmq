package io.pan.rabbitmq.direct;

import com.rabbitmq.client.Channel;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import static io.pan.rabbitmq.direct.Console.PLAYSTATION;
import static io.pan.rabbitmq.direct.Console.SWITCH;
import static io.pan.rabbitmq.direct.Console.XBOX;

public class DirectExchange {

  public static void main(String[] args) {

    final Supplier<Channel> channel = new PreparedChannel();

    CompletableFuture.allOf(
        asyncPublisher(channel.get(), PLAYSTATION),
        asyncPublisher(channel.get(), XBOX),
        asyncPublisher(channel.get(), SWITCH),
        asyncConsumer(channel.get(), PLAYSTATION),
        asyncConsumer(channel.get(), XBOX),
        asyncConsumer(channel.get(), SWITCH)
    ).join();

  }

  private static CompletableFuture<Void> asyncPublisher(Channel channel, Console console) {
    return CompletableFuture.runAsync(
        () -> new DirectPublisher(
            channel,
            console.routingKey()
        ).start()
    );
  }

  private static CompletableFuture<Void> asyncConsumer(Channel channel, Console console) {
    return CompletableFuture.runAsync(
        () -> new Consumer(
            channel,
            console.queueName()
        ).start()
    );
  }
}
