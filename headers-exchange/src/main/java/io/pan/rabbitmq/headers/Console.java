package io.pan.rabbitmq.headers;

public enum Console {

  PLAYSTATION("PlayStation", "playstation", "sony"),
  XBOX("Xbox", "xbox", "microsoft"),
  SWITCH("Switch", "switch", "nintendo");

  private final String queueName;
  private final String firstHeader;
  private final String secondHeader;


  Console(String queueName, String firstHeader, String secondHeader) {
    this.queueName = queueName;
    this.firstHeader = firstHeader;
    this.secondHeader = secondHeader;
  }

  public String queueName() {
    return queueName;
  }

  public String firstHeader() {
    return firstHeader;
  }

  public String secondHeader() {
    return secondHeader;
  }
}
