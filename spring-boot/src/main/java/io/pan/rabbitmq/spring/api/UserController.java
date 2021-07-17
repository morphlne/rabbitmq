package io.pan.rabbitmq.spring.api;

import io.pan.rabbitmq.spring.dto.UserDTO;
import io.pan.rabbitmq.spring.service.UserPublisher;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

  private final UserPublisher users;

  @PostMapping
  public String publish(@RequestBody UserDTO user) {
    users.publish(user);
    return "OK";
  }
}
