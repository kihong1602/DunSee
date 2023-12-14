package com.dfo.dunsee.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginDto {

  private String username;
  private String password;

  @Builder
  private LoginDto(String username, String password) {
    this.username = username;
    this.password = password;
  }
}
