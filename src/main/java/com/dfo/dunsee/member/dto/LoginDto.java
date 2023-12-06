package com.dfo.dunsee.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginDto {

  private String username;
  private String password;

  @Builder
  private LoginDto(String username, String password) {
    this.username = username;
    this.password = password;
  }
}
