package com.dfo.dunsee.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {
  USER("ROLE_USER"),
  MANAGER("ROLE_MANAGER"),
  ADMIN("ROLE_ADMIN");

  private final String value;


}
