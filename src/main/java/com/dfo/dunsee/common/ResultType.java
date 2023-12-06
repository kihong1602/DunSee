package com.dfo.dunsee.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultType {

  SUCCESS("success"),
  FAILURE("failure"),
  EXIST("exist");

  private final String description;

}
