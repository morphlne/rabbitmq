package io.pan.rabbitmq.spring.publisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringCloudStreamPublisher {

  public static void main(String[] args) {
    new SpringApplication(SpringCloudStreamPublisher.class).run(args);
  }

}
