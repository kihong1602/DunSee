package com.dfo.dunsee.member.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.dfo.dunsee.common.ResultType;
import com.dfo.dunsee.common.ServiceCode;
import com.dfo.dunsee.member.dto.JoinMemberInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class RegisterServiceTest {

  @Autowired
  private RegisterService registerService;

  private ServiceCode serviceCode;
  private JoinMemberInfo alreadySaveMember;

  @BeforeEach
  void Before() {
    serviceCode = ServiceCode.MBR101;
    alreadySaveMember = JoinMemberInfo.builder().username("test1").password("test").email("test@test.com").build();
    registerService.memberRegister(serviceCode, alreadySaveMember);
  }

  @Test
  @DisplayName("ResultType이 SUCCESS가 입력되면 회원가입을 진행한다.")
  void saveMemberSuccess() {
    //given
    JoinMemberInfo testJoinMemberInfo = JoinMemberInfo.builder()
        .username("blanc1602")
        .password("1234")
        .email("blanc1601@gmail.com")
        .build();

    //when
    ResultType result = registerService.memberRegister(serviceCode, testJoinMemberInfo);
    //then
    assertThat(result).isEqualTo(ResultType.SUCCESS);
  }

  @Test
  @DisplayName("중복회원이라면 EXIST를  출력한다.")
  void saveMemberExist() {
    //given

    //when
    ResultType result = registerService.memberRegister(serviceCode, alreadySaveMember);
    //then
    assertThat(result).isEqualTo(ResultType.EXIST);
  }

}