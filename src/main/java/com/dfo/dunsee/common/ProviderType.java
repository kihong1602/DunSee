package com.dfo.dunsee.common;

import lombok.Getter;

@Getter
public enum ProviderType {

  GOOGLE("google"),
  KAKAO("kakao");

  private final String provider;

  ProviderType(String provider) {
    this.provider = provider;
  }
}
