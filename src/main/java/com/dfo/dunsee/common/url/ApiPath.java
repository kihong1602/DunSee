package com.dfo.dunsee.common.url;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ApiPath {
  DEFAULT_URL("https://api.neople.co.kr/df/servers/"),
  CHAR_IMAGE_URL("https://img-api.neople.co.kr/df/servers/"),
  ITEM_IMAGE_URL("https://img-api.neople.co.kr/df/items/");
  private final String url;

  public String url() {
    return url;
  }
}
