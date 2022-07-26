package io.pan.rabbitmq.spring.subscriber.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebFluxSubscriber {

    public static void main(String[] args) {
        new SpringApplication(WebFluxSubscriber.class).run(args);
    }

}
