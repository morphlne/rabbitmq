package io.pan.rabbitmq.amqp.client;

import java.util.concurrent.CompletableFuture;

public class AmqpClient {

  public static void main(String[] args) {

    final String queueName = "Queue-1";
    final PreparedChannel channel = new PreparedChannel();

    CompletableFuture.allOf(
        CompletableFuture.runAsync(
            () -> new Producer(
                channel.get(),
                queueName
            ).start()
        ),
        CompletableFuture.runAsync(
            () -> new Consumer(
                channel.get(),
                queueName,
                "first"
            ).start()
        ),
        CompletableFuture.runAsync(
            () -> new Consumer(
                channel.get(),
                queueName,
                "second"
            ).start()
        )
    ).join();

  }
}
