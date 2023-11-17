package com.dfo.dunsee.common;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ApiUri {

  SERVERS("servers"),
  CHARACTERS("characters");

  private final String value;

  public String value() {
    return value;
  }
}
