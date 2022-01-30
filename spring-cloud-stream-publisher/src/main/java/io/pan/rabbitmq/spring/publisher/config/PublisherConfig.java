package io.pan.rabbitmq.spring.publisher.config;

import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(OutputMessageChannel.class)
public class PublisherConfig {}
