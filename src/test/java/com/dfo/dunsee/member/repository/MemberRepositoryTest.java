package com.dfo.dunsee.member.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.dfo.dunsee.domain.member.entity.Member;
import com.dfo.dunsee.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
class MemberRepositoryTest {

  @Autowired
  private MemberRepository memberRepository;

  private Member savedMember;

  @BeforeEach
  void before() {
    savedMember = Member.builder().username("testMember").password("1234").email("test@test.com").build();
    memberRepository.save(savedMember);
  }


  @Test
  @DisplayName("username을 입력해 회원이 있는지 조회한다.")
  void findByUsername() {
    //given

    //when
    Member findMember = memberRepository.findByUsername(savedMember.getUsername());
    //then
    assertThat(findMember).isEqualTo(savedMember);
  }

  @Test
  @DisplayName("email을 입력해 회원이 존재하는지 조회한다.")
  void findByEmailTest() {
    //given

    //when
    Member findMember = memberRepository.findByEmail(savedMember.getEmail());

    //then
    assertThat(findMember).isEqualTo(savedMember);
  }
}