package com.dfo.dunsee.common;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ApiPath {
  DEFAULT_URL("https://api.neople.co.kr/df/"),
  IMAGE_URL("https://img-api.neople.co.kr/df/");

  private final String url;

  public String url() {
    return url;
  }
}
