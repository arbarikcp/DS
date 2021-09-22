package Streaming.Basic.Api.Model.Spec;


import lombok.Getter;

public class Component {

  @Getter
  private String name;

  @Getter
  private Stream outGoingStream = new Stream();

  public Component(String name) {
    this.name = name;
  }
}
