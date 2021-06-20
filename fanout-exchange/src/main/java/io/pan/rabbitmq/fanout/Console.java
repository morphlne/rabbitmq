package io.pan.rabbitmq.fanout;

public enum Console {

  PLAYSTATION("PlayStation"),
  XBOX("Xbox"),
  SWITCH("Switch");

  private final String queueName;

  Console(String queueName) {
    this.queueName = queueName;
  }

  public String queueName() {
    return queueName;
  }

  public String routingKey() {
    return queueName.toLowerCase();
  }
}
