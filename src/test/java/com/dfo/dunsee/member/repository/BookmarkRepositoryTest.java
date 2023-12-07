package com.dfo.dunsee.member.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.dfo.dunsee.member.entity.CharacterInfo;
import com.dfo.dunsee.member.entity.Member;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookmarkRepositoryTest {


  @Autowired
  private BookmarkRepository bookmarkRepository;

  @Test
  @DisplayName("멤버 엔티티를 입력하면 캐릭터정보가 반환된다.")
  void findCharacterInfoByMemberTest() {
    //given
    Member member = Member.builder().id(1).build();

    //when
    List<CharacterInfo> result = bookmarkRepository.findCharacterInfoByMember(member);

    //then
    assertThat(result).hasSize(2);
    assertThat(result.get(0).getImgUrl()).isEqualTo(
        "https://img-api.neople.co.kr/df/servers/hilder/characters/e5ef51c6e6b6f9b34e87b9ab242415de?zoom=2");
    assertThat(result.get(1).getImgUrl()).isEqualTo(
        "https://img-api.neople.co.kr/df/servers/hilder/characters/cbd86f898b1ef7f0782f967b2ac9699b?zoom=2");

  }
}