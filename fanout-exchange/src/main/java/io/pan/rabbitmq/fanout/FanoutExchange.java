package io.pan.rabbitmq.fanout;

import com.rabbitmq.client.Channel;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import static io.pan.rabbitmq.fanout.Console.PLAYSTATION;
import static io.pan.rabbitmq.fanout.Console.SWITCH;
import static io.pan.rabbitmq.fanout.Console.XBOX;

public class FanoutExchange {

  public static void main(String[] args) {

    final Supplier<Channel> channel = new PreparedChannel();

    CompletableFuture.allOf(
        asyncPublisher(channel.get()),
        asyncConsumer(channel.get(), PLAYSTATION),
        asyncConsumer(channel.get(), XBOX),
        asyncConsumer(channel.get(), SWITCH)
    ).join();

  }

  private static CompletableFuture<Void> asyncPublisher(Channel channel) {
    return CompletableFuture.runAsync(
        () -> new FanoutPublisher(
            channel
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
