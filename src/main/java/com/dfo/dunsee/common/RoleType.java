package com.dfo.dunsee.common;

import lombok.Getter;

@Getter
public enum RoleType {
  USER("ROLE_USER"),
  ADMIN("ROLE_ADMIN");

  private final String value;

  RoleType(String value) {
    this.value = value;
  }

}
