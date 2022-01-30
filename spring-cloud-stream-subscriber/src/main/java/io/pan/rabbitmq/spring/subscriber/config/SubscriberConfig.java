package io.pan.rabbitmq.spring.subscriber.config;

import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(InputMessageChannel.class)
public class SubscriberConfig {}
