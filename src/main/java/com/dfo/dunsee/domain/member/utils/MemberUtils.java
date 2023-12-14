package com.dfo.dunsee.domain.member.utils;

import com.dfo.dunsee.domain.member.dto.JoinMemberInfo;
import com.dfo.dunsee.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberUtils {

  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public Member joinMemberInfoToMember(JoinMemberInfo joinMemberInfo) {

    String username = joinMemberInfo.getUsername();
    String encodedPassword = passwordEncoding(joinMemberInfo.getPassword());
    String email = joinMemberInfo.getEmail();

    return Member.builder().username(username).password(encodedPassword).email(email).build();
  }

  private String passwordEncoding(String password) {
    return bCryptPasswordEncoder.encode(password);
  }
}
