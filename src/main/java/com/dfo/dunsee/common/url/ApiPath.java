package com.dfo.dunsee.common.url;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ApiPath {
  DEFAULT_URL("https://api.neople.co.kr/df/servers/"),
  IMAGE_URL("https://img-api.neople.co.kr/df/servers/");

  private final String url;

  public String url() {
    return url;
  }
}
