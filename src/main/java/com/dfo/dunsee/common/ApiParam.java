package com.dfo.dunsee.common;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ApiParam {
  CHARACTER_NAME("characterName"),
  LIMIT("limit"),
  WORD_TYPE("wordType"),
  API_KEY("apikey"),
  ZOOM("zoom");

  private final String value;

  public String value() {
    return value;
  }
}
