package com.dfo.dunsee.common.url;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ApiParam {
  LIMIT("limit"),
  WORD_TYPE("wordType"),
  API_KEY("apikey"),
  ZOOM("zoom");

  private final String value;

  public String value() {
    return value;
  }
}
