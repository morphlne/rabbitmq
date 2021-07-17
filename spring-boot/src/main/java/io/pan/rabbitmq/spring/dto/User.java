package io.pan.rabbitmq.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class User implements Serializable {

  private final UUID id;
  private final String name;

  public User(String name) {
    this(UUID.randomUUID(), name);
  }
}
