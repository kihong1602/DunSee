package com.dfo.dunsee.domain.member.utils;

import static org.assertj.core.api.Assertions.assertThat;

import com.dfo.dunsee.domain.member.dto.JoinMemberInfo;
import com.dfo.dunsee.domain.member.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class MemberUtilsTest {

  @Autowired
  private MemberUtils memberUtils;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;
  private JoinMemberInfo joinMemberInfo;


  @BeforeEach
  void before() {
    joinMemberInfo = JoinMemberInfo.builder().username("testUser").password("test").email("test@test.com").build();
  }

  @Test
  @DisplayName("JoinMemberInfo를 입력하면 Member를 반환하고, 비밀번호 인코딩을 확인한다.")
  void joinMemberInfoConvertMemberAndPWValidation() {
    //given
    Member member = memberUtils.joinMemberInfoToMember(joinMemberInfo);

    //when
    boolean matches = bCryptPasswordEncoder.matches(joinMemberInfo.getPassword(), member.getPassword());

    //then
    assertThat(matches).isTrue();
  }
}