package com.dfo.dunsee.member.utils;

import com.dfo.dunsee.member.dto.JoinMemberInfo;
import com.dfo.dunsee.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberUtils {

  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public Member joinMemberInfoToMember(JoinMemberInfo joinMemberInfo) {
    String encodedPassword = passwordEncoding(joinMemberInfo.getPassword());
    return Member.builder()
                 .username(joinMemberInfo.getUsername())
                 .password(encodedPassword)
                 .email(joinMemberInfo.getEmail())
                 .build();
  }

  private String passwordEncoding(String password) {
    return bCryptPasswordEncoder.encode(password);
  }
}
