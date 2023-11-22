package com.dfo.dunsee.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResultType {

  SUCCESS("success"),
  FAILURE("failure");

  private final String description;

}
