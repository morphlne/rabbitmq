package io.pan.rabbitmq.spring.subscriber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCloudStreamSubscriber {

  public static void main(String[] args) {
    new SpringApplication(SpringCloudStreamSubscriber.class).run(args);
  }

}
