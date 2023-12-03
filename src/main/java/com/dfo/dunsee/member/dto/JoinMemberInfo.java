package com.dfo.dunsee.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class JoinMemberInfo {

  private String username;
  private String password;
  private String email;

  @Builder
  private JoinMemberInfo(String username, String password, String email) {
    this.username = username;
    this.password = password;
    this.email = email;
  }
}
