package com.dfo.dunsee.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProviderType {

  GOOGLE("google"),
  KAKAO("kakao"),
  DUNSEE("dunsee");

  private final String provider;

}
