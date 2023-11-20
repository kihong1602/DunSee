package com.dfo.dunsee.common.url;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum WordType {
  FULL("full"),
  MATCH("match");

  private final String value;

  public String value() {
    return value;
  }
}
