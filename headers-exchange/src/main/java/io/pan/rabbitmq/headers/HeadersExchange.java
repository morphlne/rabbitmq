package io.pan.rabbitmq.headers;

import com.rabbitmq.client.Channel;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import static io.pan.rabbitmq.headers.Console.PLAYSTATION;
import static io.pan.rabbitmq.headers.Console.SWITCH;
import static io.pan.rabbitmq.headers.Console.XBOX;

public class HeadersExchange {

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
        () -> new HeadersPublisher(
            channel,
            console.firstHeader(),
            console.secondHeader(),
            console.name()
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
